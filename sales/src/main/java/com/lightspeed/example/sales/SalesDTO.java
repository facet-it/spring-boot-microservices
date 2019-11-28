package com.lightspeed.example.sales;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SalesDTO {

    private Integer customerId;
    private Long date;
    private List<SalesLineDTO> items;

}
