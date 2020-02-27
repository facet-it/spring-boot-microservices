package com.lightspeed.restaurant.usage.analytics;

import com.lightspeed.restaurant.usage.EndpointUsedEvent;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.java.Log;

@Log
public class UsageAggregate {

    private final Integer companyId;
    private Map<String, Integer> aggregateTable = new HashMap<>();

    public UsageAggregate(Integer companyId) {
        this.companyId = companyId;
    }

    public void registerEndpointUsage(EndpointUsedEvent event) {
        log.info("Registering an event for company with id " + this.companyId);
        if(aggregateTable.containsKey(event.getEndpoint())) {
            Integer count = aggregateTable.get(event.getEndpoint());
            aggregateTable.put(event.getEndpoint(), ++ count);
        }
        else {
            aggregateTable.put(event.getEndpoint(), 1);
        }
    }

    public Map<String, Integer> getAnalytics() {
        return new HashMap<>(this.aggregateTable);
    }

    public Integer getCompanyId() {
        return this.companyId;
    }

}
