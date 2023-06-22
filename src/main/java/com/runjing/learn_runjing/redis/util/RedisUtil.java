package com.runjing.learn_runjing.redis.util;

import com.runjing.learn_runjing.redis.config.RedisConfiguration;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author forestSpringH
 * @date 2023/6/21
 * @project learn_runjing
 */
@Component
@ConditionalOnBean({RedisConfiguration.class,RedisTemplate.class})
public class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    /**插入新的String 键值对 如果存在则覆盖*/
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }
    /**插入新的String 键值对 并设置过期时间 单位为s 如果存在则覆盖*/
    public void setEx(String key, Object value,Long expire){
        redisTemplate.opsForValue().set(key, value,expire, TimeUnit.SECONDS);
    }
    /**如果存在key则不插入，不存在则插入，并设置过期时间 单位为s 如果后续set相同的key则当前插入的值会被覆盖*/
    public Boolean setNx(String key, Object value, Long expire) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, Duration.ofSeconds(expire));
    }

    /**插入地址信息 经纬度 地址名称 地址密钥*/
    public Long setGeo(double latitude,double longitude,String address,String geoKey){
        return redisTemplate.opsForGeo().add(geoKey, new Point(latitude, longitude), address);
    }
    /**获取某一地址的点列表 由于可能存在某一地址多点附近 故返回列表 后续开发可在确保地址加上某些用户独特信息时可以返回pointList.get(0)*/
    public List<Point> getGeoPoint(String geoKey,String address){
        return redisTemplate.opsForGeo().position(geoKey, address);
    }
    /**获取某一点附近多少长度（默认m为基本单位）内的地址与点信息*/
    public Map<String,Point> getNeighborhoodMap(String geoKey,double latitude,double longitude,double distance){
        Map<String,Point> neighborhoodMap = new HashMap<>();
        GeoResults<RedisGeoCommands.GeoLocation<Object>> search = redisTemplate.opsForGeo().search(geoKey, new Circle(latitude, longitude, distance));
        if (Objects.nonNull(search) && !CollectionUtils.isEmpty(search.getContent())) {
            search.getContent().forEach(geoLocationGeoResult -> {
                Point point = geoLocationGeoResult.getContent().getPoint();
                String name = (String) geoLocationGeoResult.getContent().getName();
                neighborhoodMap.put(name,point);
            });
        }
        return neighborhoodMap;
    }
    /**移除地址点*/
    public Boolean removeGeoPoint(String geoKey,double latitude, double longitude){
        GeoResults<RedisGeoCommands.GeoLocation<Object>> search = redisTemplate.opsForGeo().search(geoKey, new Circle(latitude, longitude, 0L));
        if (Objects.nonNull(search)){
            Object name = search.getContent().get(0).getContent().getName();
            return Objects.nonNull(redisTemplate.opsForGeo().remove(geoKey, name));
        }else {
            return true;
        }

    }

    /**某一键为key的键值对是否存在*/
    public Boolean exists(String key) {
        return Objects.nonNull(get(key));
    }
    /**获取键值为key的值*/
    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }
    /**获取某一键值对的TTL，为-1则是永不过期，但当redis内存满载时也会根据自我的八个淘汰策略进行删除*/
    public Long getTtl(String key){
        return redisTemplate.opsForValue().getOperations().getExpire(key,TimeUnit.SECONDS);
    }
    /**判断某一键值为key的键值对是否是存在过期属性的*/
    public Boolean judgeKeyWillExpire(String key){
        return !Objects.equals(getTtl(key).intValue(),-1);
    }
    /**向链表名为listKey的链表左端插入新的元素，它的值为value*/
    public Long leftPush(String listKey,Object value) {
        Long size = redisTemplate.opsForList().size(listKey);
        if (Objects.nonNull(size) && Objects.equals(size.intValue(), 0)) {
            return redisTemplate.opsForList().leftPush(listKey, value);
        }else {
            redisTemplate.opsForList().set(listKey, 0L,value);
            return 0L;
        }
    }
    /**从链表名为listKey的链表中右侧吐出一个元素*/
    public Object rightPop(String listKey){
        return redisTemplate.opsForList().rightPop(listKey);
    }

    /**删除键值为key的键值对*/
    public Boolean remove(String key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }
}
