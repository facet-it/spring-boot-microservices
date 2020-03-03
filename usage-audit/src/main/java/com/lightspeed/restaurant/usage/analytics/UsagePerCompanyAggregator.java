package com.lightspeed.restaurant.usage.analytics;

import com.google.gson.Gson;

import com.lightspeed.restaurant.usage.EndpointUsedEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import lombok.extern.java.Log;

@Component
@Log
public class UsagePerCompanyAggregator {

    private static final int MAX_EVENT_COUNT = 50;

    @Autowired
    private Function<Integer, UsageAggregate> toCompanyUsageAggregate;

    @Autowired
    private UsageSnapshotRepository snapshotRepository;

    @Autowired
    private Gson gson;

    private Map<Integer, UsageAggregate> companyToAggregateRegistry = new HashMap<>();
    private int eventCount = 0;

    @KafkaListener(topics = "usage-audit", groupId = "usage-per-company")
    public void aggregatePerCompany(EndpointUsedEvent event) {
        this.eventCount ++;
        Integer companyId = event.getCompanyDetails().getOid();

        if(companyToAggregateRegistry.containsKey(companyId)) {
            companyToAggregateRegistry.get(companyId).registerEndpointUsage(event);
        }
        else {
            UsageAggregate aggregate = toCompanyUsageAggregate.apply(companyId);
            companyToAggregateRegistry.put(companyId, aggregate);
            aggregate.registerEndpointUsage(event);
        }

        if(eventCount == MAX_EVENT_COUNT) {
            takeSnapshot();
        }
    }

    private void takeSnapshot() {
        UsageSnapshot snapshot = new UsageSnapshot();
        snapshot.setTime(Instant.now().toEpochMilli());

        snapshot.setAggregate(gson.toJson(this.companyToAggregateRegistry));
        snapshotRepository.save(snapshot);
        this.eventCount = 0;
    }

}
