package com.lightspeed.redis.test.result;

import com.lightspeed.redis.test.internal.PublisherStopped;
import com.lightspeed.redis.test.internal.TestStarted;
import com.lightspeed.redis.test.worker.run.TestMessage;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
class ResultHandler {

    private Map<Long, Map<String, List<TestMessage>>> publishedMessagesPerTest = new HashMap<>();

    @EventListener
    public synchronized void handleTestStarted(TestStarted message) {
        this.publishedMessagesPerTest.put(message.getTestId(), new HashMap<>());
    }

    @EventListener
    public synchronized void handlePublisherStopped(PublisherStopped message) {
        Map<String, List<TestMessage>> messagesSentForTest = this.publishedMessagesPerTest.get(message.getTestId());
        messagesSentForTest.put(message.getPublisherId(), message.getMessagesSent());
    }

    Map<String, List<TestMessage>> getResultFor(long testId) {
        Map<String, List<TestMessage>> copy = new HashMap<>();
        copy.putAll(this.publishedMessagesPerTest.get(testId));
        return copy;
    }

}
