package com.lightspeed.restaurant.usage.analytics;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsageSnapshotRepository extends CrudRepository<UsageSnapshot, Long> {

    @Query(value = "Select aggregate from enpoint_usage_snapshot where time = (select max(time) from enpoint_usage_snapshot)",
    nativeQuery = true)
    public String getLatestSnapshot();
}
