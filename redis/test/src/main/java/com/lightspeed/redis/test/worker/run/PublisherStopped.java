package com.lightspeed.redis.test.run;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PublisherStopped implements Serializable {
    private List<TestMessage> messagesSent = new ArrayList<>();

    public PublisherStopped(List<TestMessage> messagesSent) {
        this.messagesSent.addAll(messagesSent);
    }

    public List<TestMessage> getMessagesSent() {
        List<TestMessage> listCopy = new ArrayList<>();
        listCopy.addAll(this.messagesSent);
        return listCopy;
    }
}
