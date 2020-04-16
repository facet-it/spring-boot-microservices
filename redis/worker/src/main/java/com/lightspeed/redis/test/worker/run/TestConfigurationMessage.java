package com.lightspeed.redis.test.worker;

import java.io.Serializable;

import lombok.Value;

@Value
public class TestConfigurationMessage implements Serializable {

    private long testId;
    private int durationInSeconds;
    private int numberOfMessagesPerSecond;
    private String date;
    private String topicToTest;

}
