package com.lightspeed.redis.test.instance;

import com.lightspeed.redis.test.TestMessage;

import org.redisson.Redisson;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.time.Instant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedisPubSubPair {

    private RedissonClient client;
    private RTopic testTopic;

    public RedisPubSubPair() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        this.client =  Redisson.create(config);
        this.testTopic = client.getTopic("loadtest");

        testTopic.addListener(TestMessage.class, new SaveMessages());
    }

    public void startLoad() {
        for(int i=0; i < 5; i++) {

            TestMessage message = new TestMessage("pubsubpair1", 1L, Instant.now().toEpochMilli());
            try{
                Thread.sleep(500);
            }
            catch(InterruptedException ie) {
                log.info("got interrupted");
            }
        }
    }

}
