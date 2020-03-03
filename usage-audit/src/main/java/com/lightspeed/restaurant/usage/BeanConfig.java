package com.lightspeed.restaurant.usage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.lightspeed.restaurant.usage.analytics.UsageAggregate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.function.Function;

@Configuration
public class BeanConfig {

    @Bean
    public Function<Integer, UsageAggregate> toCompanyUsageAggregate() {
        return this::usageAggregate;
    }

    @Bean
    @Scope("prototype")
    public UsageAggregate usageAggregate(Integer companyId) {
        return new UsageAggregate(companyId);
    }

    @Bean
    public Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.enableComplexMapKeySerialization();

        return gsonBuilder.create();
    }
}
