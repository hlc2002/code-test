package com.runjing.learn_runjing.redis.redisson;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author forestSpringH
 * @date 2023/7/14
 * @project learn_runjing
 */
@Component("RedissonLockService")
@ConditionalOnBean(RedissonConfiguration.class)
@Slf4j
public class RedissonLockService {
    @Resource
    private RedissonClient redisson;

    public Boolean lock(String key) {
        return lock(key, 10L);
    }

    public Boolean redLock(String key) {
        return redLock(key, 10L);
    }

    public Boolean lock(String key, Long expireTime) {
        if (Objects.isNull(redisson)) {
            log.warn("redisson客户端对象为null");
            return Boolean.FALSE;
        }
        try {
            RLock lock = redisson.getLock(key);
            if (lock.isLocked()) {
                log.info("锁：{}已被获取，当前请求阻塞",key);
                return Boolean.TRUE;
            }
            lock.lock(expireTime, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            log.warn("获取锁异常");
            return Boolean.TRUE;
        }
    }

    public Boolean redLock(String key, Long expireTime) {
        if (Objects.isNull(redisson)) {
            log.warn("redisson客户端对象为null");
            return Boolean.FALSE;
        }
        try {
            RLock lock = redisson.getLock(key);
            RedissonRedLock redLock = new RedissonRedLock(lock);
            return redLock.tryLock(expireTime, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.warn("获取红锁异常");
            return Boolean.FALSE;
        }
    }

    public Boolean tryLock(String key,Long waitTime,long timeout, TimeUnit unit){
        if (Objects.isNull(redisson)) {
            log.warn("redisson客户端对象为null");
            return Boolean.FALSE;
        }
        try{
            RLock lock = redisson.getLock(key);
            if (lock.isLocked()) {
                log.info("锁：{}已被获取，当前请求阻塞",key);
                return Boolean.TRUE;
            }
            return lock.tryLock(waitTime,timeout,unit);
        }catch(Exception e){
            log.warn("重入锁异常");
            return Boolean.FALSE;
        }
    }

    public Boolean unlock(String key) {
        if (Objects.isNull(redisson)) {
            log.warn("redisson客户端对象为null");
            return Boolean.FALSE;
        }
        try {
            RLock lock = redisson.getLock(key);
            if (lock.isLocked()) {
                log.info("锁：{}释放",key);
               lock.unlock();
            }
            return Boolean.TRUE;
        }catch (Exception e){
            log.warn("释放锁异常");
            return Boolean.FALSE;
        }
    }

    public Boolean redUnlock(String key) {
        if (Objects.isNull(redisson)) {
            log.warn("redisson客户端对象为null");
            return Boolean.FALSE;
        }
        try {
            RLock lock = redisson.getLock(key);
            RedissonRedLock redLock = new RedissonRedLock(lock);
            redLock.unlock();
            return Boolean.TRUE;
        }catch (Exception e){
            log.warn("释放红锁异常");
            return Boolean.FALSE;
        }
    }
}
