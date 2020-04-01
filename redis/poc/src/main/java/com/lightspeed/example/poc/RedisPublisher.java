package com.lightspeed.example.poc;

import org.redisson.api.RTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RedisPublisher {

    @Autowired
    private RTopic chatTopic;

    public void sendMessage() {
        long clientsReceivedMessage = chatTopic.publish("Hello world");
    }

    public void sendMessage(String message) {
        long received = chatTopic.publish(message);
        log.info("this the received value: " + received);
    }

    public void sendChatMessage(ChatMessage message) {
        long received = chatTopic.publish(message);
        log.info("this the received value: " + received);
    }
}
