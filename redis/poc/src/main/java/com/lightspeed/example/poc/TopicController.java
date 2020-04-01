package com.lightspeed.example.poc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/redis")
@Slf4j
public class TopicController {

    @Autowired
    private RedisPublisher publisher;

    @GetMapping("/hello")
    public void sendHelloWorld() {
        log.info("about to send message");
        publisher.sendMessage();
        log.info("message sent");
    }

    @GetMapping("/send")
    public void sendMessage(@RequestParam(value = "message") String message) {
        publisher.sendMessage(message);
    }

    @GetMapping("/chat")
    public void sendMessage(@RequestParam(value = "username") String username,
                            @RequestParam(value = "message") String message) {
        ChatMessage chatMessage = new ChatMessage(username, message);
        publisher.sendChatMessage(chatMessage);
    }

}
