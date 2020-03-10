package com.lightspeed.restaurant.usage.analytics;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Map;


@Service
public class UsagePerCompanyLookup {

    private UsageSnapshotRepository snapshotRepository;
    private Gson gson;

    @Autowired
    public UsagePerCompanyLookup(UsageSnapshotRepository snapshotRepository, Gson gson) {
        this.snapshotRepository = snapshotRepository;
        this.gson = gson;
    }

    public Map<Integer, UsageAggregate> getLatestSnapshot() {
        String companyAggregate = snapshotRepository.getLatestSnapshot();

        Type aggregateType = new TypeToken<Map<Integer, UsageAggregate>>() {}.getType();
        Map<Integer, UsageAggregate> latestAggregate = gson.fromJson(companyAggregate, aggregateType);

        return latestAggregate;
    }
}
