package com.lightspeed.example.products;

import java.io.Serializable;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductDTO implements Serializable {

    private String plu;
    private String name;
    private String description;
    private double price;
    private Integer loyaltyPoints;

}
