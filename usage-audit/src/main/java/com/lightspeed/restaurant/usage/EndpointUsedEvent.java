package com.lightspeed.restaurant.usage;

import lombok.Data;

@Data
public class EndpointUsedEvent {

    private UserDetails userDetails;
    private CompanyDetails companyDetails;
    private String endpoint;
    private String arguments;
}
