package com.lightspeed.redis.test.internal;

import com.lightspeed.redis.test.worker.run.TestMessage;

import java.io.Serializable;
import java.util.List;

import lombok.Value;

@Value
public class HandlePublishedMessages implements Serializable {

    private long testId;
    private String publisherId;
    private List<TestMessage> messagesSent;

}
