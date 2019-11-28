package com.lightspeed.example.sales;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinesRepository extends CrudRepository<SalesLine, Long> {

}
