package com.lightspeed.example.sales;

import java.util.List;

import lombok.Data;
import lombok.Singular;

@Data
public class SaleCreatedEvent {

    private long date;
    private int customerId;

    @Singular
    private List<String> products;
}
