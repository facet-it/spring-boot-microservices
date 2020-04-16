package com.lightspeed.redis.test.internal;

import java.io.Serializable;

import lombok.Value;

@Value
public class GetResult implements Serializable {
    private long testId;
}
