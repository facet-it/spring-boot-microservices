package com.lightspeed.restaurant.usage;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompanyDetails {

    private int oid;
    private String type;
    private String name;
    private String city;
    private String country;
}
