package com.lightspeed.example.products;

import java.io.Serializable;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SalesEvent implements Serializable {

    private long date;
    private int customerId;

}
