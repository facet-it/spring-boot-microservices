package com.lightspeed.redis.test.worker.run;

import java.io.Serializable;

import lombok.Value;

@Value
public class TestMessage implements Serializable {

    private String sender;
    private long testId;
    private long messageId;
    private long sendDate;

}
