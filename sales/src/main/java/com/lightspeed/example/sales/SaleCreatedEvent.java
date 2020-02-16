package com.lightspeed.example.sales;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
public class SaleCreatedEvent implements Serializable {

    private long date;
    private int customerId;
    private List<Line> lines = new LinkedList<>();

    public SaleCreatedEvent(long date, int customerId, List<SalesLine> products) {
        this.date = date;
        this.customerId = customerId;
        setProducts(products);
    }

    private void setProducts(List<SalesLine> products) {
        List<Line> productLines = products.stream()
            .map(salesLine -> new Line(salesLine.getProduct(), salesLine.getAmount()))
            .collect(Collectors.toList());
        this.lines.addAll(productLines);
    }

    @Value
    @AllArgsConstructor
    private class Line implements Serializable {

        private Integer productId;
        private Integer amount;
    }
}
