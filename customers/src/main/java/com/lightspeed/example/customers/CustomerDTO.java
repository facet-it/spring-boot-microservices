package com.lightspeed.example.customers;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CustomerDTO {

    private String fullName;
    private String email;
    private Integer loyaltyAmount;
}
