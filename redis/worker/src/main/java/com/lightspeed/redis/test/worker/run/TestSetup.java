package com.lightspeed.redis.test.worker.run;

import com.lightspeed.redis.test.worker.TestConfigurationMessage;

import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

@Component
class TestSetup {

    @Autowired
    private RTopic configurationTopic;

    @Autowired
    private RedissonClient client;

    private Map<Long, RedisTestPublisher> testRegistry = new HashMap<>();

    @PostConstruct
    private void postConstruct() {
        configurationTopic.addListener(TestConfigurationMessage.class, this::handleSetupMessage);
        configurationTopic.addListener(StartTest.class, this::handleStartTestMessage);
    }


    private synchronized void handleSetupMessage(CharSequence topic, TestConfigurationMessage message) {
        RTopic currentTestTopic = client.getTopic(message.getTopicToTest());
        RedisTestPublisher testPublisher = new RedisTestPublisher(message.getTestId(),
                                                                  configurationTopic,
                                                                  currentTestTopic,
                                                                  message.getNumberOfMessagesPerSecond(),
                                                                  message.getDurationInSeconds());
        testRegistry.put(message.getTestId(), testPublisher);

        //signal that the test setup is done and ready to be run
        this.configurationTopic.publish(new SetupFinished(message.getTestId()));
    }

    private synchronized void handleStartTestMessage(CharSequence topic, StartTest message) {
        RedisTestPublisher testPublisher = testRegistry.get(message.getTestId());
        Thread testRunner = new Thread(testPublisher);
        testRunner.start();
        testRegistry.remove(message.getTestId());
    }

}
