package com.runjing.learn_runjing.redis.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author forestSpringH
 * @date 2023/7/14
 * @project learn_runjing
 */
@Configuration
public class RedissonConfiguration {

    @Value("${spring.data.redis.host}")
    private String redisHost;
    @Value("${spring.data.redis.port}")
    private int port;
    @Value("${spring.data.redis.password}")
    private String password;
    @Bean(destroyMethod = "shutdown",name = "redisson")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public RedissonClient redisson() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://"+redisHost+":"+port).setPassword(password);
        config.setCodec(new JsonJacksonCodec());
        return Redisson.create(config);
    }
}
