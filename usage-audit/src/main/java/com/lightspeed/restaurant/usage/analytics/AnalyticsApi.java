package com.lightspeed.restaurant.usage.analytics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("endpoint-analytics/companies")
@Slf4j
public class AnalyticsApi {

    @Autowired
    private UsagePerCompanyLookup usageLookup;

    @GetMapping("/{companyId}")
    public List<String> getTopTen(@PathVariable(value = "companyId") Integer companyId) {
        Map<Integer, UsageAggregate> snapshot = usageLookup.getLatestSnapshot();
        UsageAggregate companyAggregate = snapshot.get(companyId);
        //Note to self: this is a lookup api, it should not be able to do this:
        //companyAggregate.registerEndpointUsage(someEvent);
        return companyAggregate.getTopTenUsedEndpoints();
    }

}
