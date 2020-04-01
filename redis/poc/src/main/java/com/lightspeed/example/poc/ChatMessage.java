package com.lightspeed.example.poc;

import java.io.Serializable;

import lombok.Value;

@Value
public class ChatMessage implements Serializable {

    private String userName;
    private String message;

}
