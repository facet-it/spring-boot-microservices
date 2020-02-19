package com.lightspeed.example.sales;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@NoArgsConstructor
public class SaleCreatedEvent implements Serializable {

    private long date;
    private int customerId;
    private List<Line> lines = new LinkedList<>();

    public SaleCreatedEvent(long date, int customerId, List<Line> products) {
        this.date = date;
        this.customerId = customerId;
        this.lines.addAll(products);
    }

    @Value
    @AllArgsConstructor
    public class Line implements Serializable {

        private Integer productId;
        private Integer amount;
    }

}
