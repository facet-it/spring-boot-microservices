package com.lightspeed.restaurant.usage.analytics;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsageSnapshotRepository extends CrudRepository<UsageSnapshot, Long> {

}
