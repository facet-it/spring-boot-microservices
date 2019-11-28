package com.lightspeed.example.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/customers")
public class CustomerApi {

    @Autowired
    private CustomerCrudService crudService;

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return this.crudService.findAllCustomers();
    }

    @GetMapping("/{customerId}")
    public CustomerDTO getCustomerById(@PathVariable(name = "customerId") Integer customerId) {
        return crudService.getCustomerById(customerId);
    }

    @GetMapping("/create")
    public void createDummy() {
        Random randomgenerator = new Random();
        int randomNumber = randomgenerator.nextInt(10000000) + 1;

        Customer customer = new Customer();
        customer.setFullName("Customoer " + randomNumber);
        customer.setEmail(randomNumber + "@lightspeed.com");
        if(randomNumber % 2 == 0) {
            customer.setLoyaltyAmount(randomgenerator.nextInt(1000) + 1);
        }

        crudService.addCustomer(customer);
    }

    @GetMapping("/update/{customerId}")
    public void updateCustomerLoyalty(@PathVariable(name = "customerId") Integer customerId,
                                      @RequestParam(name = "amount") Integer amount) {
        crudService.updateLoyaltyAmountFor(customerId, amount);
    }

}
