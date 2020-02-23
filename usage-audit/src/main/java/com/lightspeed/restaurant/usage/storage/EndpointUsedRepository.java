package com.lightspeed.restaurant.usage.storage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndpointUsedRepository extends CrudRepository<EndpointUsedEntry, Integer> {

}
