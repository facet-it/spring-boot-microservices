package com.lightspeed.redis.test.run;

import java.io.Serializable;

import lombok.Value;

@Value
public class SetupFinished implements Serializable {

    private long testId;
}
