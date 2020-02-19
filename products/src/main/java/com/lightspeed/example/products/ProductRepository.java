package com.lightspeed.example.products;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Query(value = "Select sum(loyalty_points) from products where id in :productIds",
    nativeQuery = true)
    Integer getTotalLoyalty(@Param("productIds") List<Integer> productIds);
}
