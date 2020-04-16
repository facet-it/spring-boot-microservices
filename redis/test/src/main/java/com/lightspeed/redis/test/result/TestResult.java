package com.lightspeed.redis.test.result;

import com.lightspeed.redis.test.worker.run.TestMessage;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Value;

@Value
public class TestResult implements Serializable {
    private long testId;
    private Map<String, List<TestMessage>> publishedMessages;
    private List<SubscriberResult>
}
