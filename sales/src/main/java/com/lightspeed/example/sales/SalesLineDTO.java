package com.lightspeed.example.sales;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SalesLineDTO {

    private Long salesId;
    private Integer productId;
    private Integer amount;
}
