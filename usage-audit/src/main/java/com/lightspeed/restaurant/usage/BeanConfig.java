package com.lightspeed.restaurant.usage;

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
}
