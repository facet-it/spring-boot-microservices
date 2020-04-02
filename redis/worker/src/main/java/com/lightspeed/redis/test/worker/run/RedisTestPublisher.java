package com.lightspeed.redis.test.worker.run;

import org.redisson.api.RTopic;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedisTestPublisher implements Runnable {

    private final int MILLIS_IN_SECOND = 1000;

    private RTopic generalTest;
    private RTopic topicToTest;
    private List<TestMessage> sentMessages = new LinkedList<>();
    private int interval;
    private long currentMessageNumber = 1;
    private int durationInSeconds;
    private String publisherId = "PUBLISHER-";
    private long testId;

    public RedisTestPublisher(long testId, RTopic generalTest, RTopic topicToTest, int loadPerSecond, int duration) {
        this.generalTest = generalTest;
        this.topicToTest = topicToTest;
        this.durationInSeconds = duration;
        this.testId = testId;

        this.interval = MILLIS_IN_SECOND / loadPerSecond;

        this.publisherId += LocalDateTime.now().toString();
    }

    @Override
    public void run() {

        long currentTimeInMillis = Instant.now().toEpochMilli();
        long endTime = currentTimeInMillis + (durationInSeconds * MILLIS_IN_SECOND);

        while (currentTimeInMillis < endTime) {
            TestMessage message = new TestMessage(publisherId, testId, currentMessageNumber, currentTimeInMillis);
            topicToTest.publish(message);
            sentMessages.add(message);

            try {
                Thread.sleep(this.interval);
            }
            catch(InterruptedException ie) {
                log.info("got interrupted");
            }

            currentTimeInMillis = Instant.now().toEpochMilli();
        }

        generalTest.publish(new PublisherStopped(this.sentMessages));

    }
}
