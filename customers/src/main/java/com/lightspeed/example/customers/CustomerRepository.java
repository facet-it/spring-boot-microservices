package com.lightspeed.example.customers;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Customer c SET c.loyaltyAmount = :loyaltyAmount WHERE c.id = :id")
    void updateLoyaltyAmount(@Param(value = "id") Integer customerId,
                             @Param(value = "loyaltyAmount") Integer loyaltyAmount);

}
