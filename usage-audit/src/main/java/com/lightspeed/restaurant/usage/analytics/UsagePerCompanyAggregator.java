package com.lightspeed.restaurant.usage.analytics;

import com.lightspeed.restaurant.usage.EndpointUsedEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import lombok.extern.java.Log;

@Component
@Log
public class UsagePerCompanyAggregator {

    @Autowired
    private Function<Integer, UsageAggregate> toCompanyUsageAggregate;

    private Map<Integer, UsageAggregate> companyToAggregateRegistry = new HashMap<>();

    @KafkaListener(topics = "usage-audit", groupId = "usage-per-company")
    public void aggregatePerCompany(EndpointUsedEvent event) {
        Integer companyId = event.getCompanyDetails().getOid();

        if(companyToAggregateRegistry.containsKey(companyId)) {
            companyToAggregateRegistry.get(companyId).registerEndpointUsage(event);
        }
        else {
            UsageAggregate aggregate = toCompanyUsageAggregate.apply(companyId);
            companyToAggregateRegistry.put(companyId, aggregate);
            aggregate.registerEndpointUsage(event);
        }
    }

}
