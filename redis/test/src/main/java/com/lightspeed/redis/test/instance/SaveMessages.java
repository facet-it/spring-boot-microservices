package com.lightspeed.redis.test.instance;

import com.lightspeed.redis.test.TestMessage;

import org.redisson.api.listener.MessageListener;

public class SaveMessages implements MessageListener<TestMessage> {

    @Override
    public void onMessage(CharSequence charSequence, TestMessage testMessage) {
        System.out.println("Sent by: " + testMessage.getSender());
        System.out.println("Timestamp " + testMessage.getSender());
    }
}
