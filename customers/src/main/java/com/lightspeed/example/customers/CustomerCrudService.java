package com.lightspeed.example.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.Iterable;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import lombok.extern.java.Log;

@Service
@Log
public class CustomerCrudService {

    @Autowired
    private CustomerRepository repository;

    public CustomerDTO getCustomerById(int id) {
        Optional<Customer> result = repository.findById(id);

        if(result.isPresent()) {
            return result.get().toDto();
        }
        else {
            throw new IllegalArgumentException("No customer could be found with id " + id);
        }
    }

    public List<CustomerDTO> findAllCustomers() {
        List<CustomerDTO> customers = new LinkedList<>();
        Iterable<Customer> result = repository.findAll();

        result.forEach(customer -> {
            CustomerDTO dto = customer.toDto();
            customers.add(dto);
        });

        return customers;
    }

    public void addCustomer(Customer customer) {
        Customer result = repository.save(customer);
        log.info("Added new customer with id " + result.getId());
    }

    public void updateLoyaltyAmountFor(Integer customerId, Integer loyalty) {
        repository.updateLoyaltyAmount(customerId, loyalty);
    }

}
