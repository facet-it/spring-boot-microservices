package com.lightspeed.restaurant.usage.analytics;

import com.lightspeed.restaurant.usage.EndpointUsedEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<String> getTopTenUsedEndpoints() {
        List<String> sortedList = aggregateTable.entrySet().stream()
            .sorted((entry1, entry2) -> entry2.getValue() - entry1.getValue())
            .map(entry -> entry.getKey() + " - " + entry.getValue())
            .collect(Collectors.toList());

        int numberOfElements = sortedList.size();

        if(numberOfElements >= 10) {
            return sortedList.subList(0, 10);
        }
        else {
            return sortedList.subList(0, numberOfElements);
        }
    }

}
