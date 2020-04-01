package com.lightspeed.example.poc;

import org.redisson.Redisson;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    public Config getConfig() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        return config;
    }

    @Bean
    public RedissonClient getRedisClient() {
        RedissonClient client =  Redisson.create(getConfig());
        return client;
    }

    @Bean
    public RTopic getRTopic(RedissonClient client) {
        return client.getTopic("chat");
    }

}
