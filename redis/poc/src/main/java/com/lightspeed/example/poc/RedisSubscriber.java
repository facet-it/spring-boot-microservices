package com.lightspeed.example.poc;

import org.redisson.api.RTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

import javax.annotation.PostConstruct;

@Component
public class RedisSubscriber {

    @Autowired
    private RTopic chatTopic;

    private BiFunction<CharSequence, ChatMessage, Void> readChatMessage =  (sequence, message) -> {
        System.out.println("User " + message.getUserName() + " said: ");
        System.out.println(message.getMessage());

        return null;
    };


    @PostConstruct
    private void postConstruct() {
        chatTopic.addListener(String.class, ((CharSequence charSequence, String s) -> {
            System.out.println("this is the charsequence: ");
            System.out.println("### " + charSequence);
            System.out.println("this is the string");
            System.out.println("### " + s);
        }));

        chatTopic.addListener(ChatMessage.class, readChatMessage::apply);
    }
}
