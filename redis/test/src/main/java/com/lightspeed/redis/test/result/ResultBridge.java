package com.lightspeed.redis.test.result;

import com.lightspeed.redis.test.worker.run.TestMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * I don't want direct access to the handler. But getting data through events seems not feasable, because that call
 * needs to be blocking and synchronous.
 *
 * So this is the resultBridge who stands in between as a proxy. You can only query.
 */
@Component
public class ResultBridge {

    @Autowired
    private ResultHandler handler;

    public Map<String, List<TestMessage>> getResultFor(long testId) {
        return handler.getResultFor(testId);
    }

}
