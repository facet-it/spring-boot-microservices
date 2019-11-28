package com.lightspeed.example.sales;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "sales")
@Data
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id")
    private Integer customer;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "sale")
    private List<SalesLine> items = new LinkedList<>();

    @Column(name = "date")
    private long date;

    public List<SalesLine> getItems() {
        List<SalesLine> lines = new ArrayList<>(this.items.size());
        lines.addAll(this.items);
        return lines;
    }

    public void setItems(List<SalesLine> items) {
        this.items.addAll(items);
    }

    public void addItems(SalesLine... lines) {
        List<SalesLine> allLines = Arrays.asList(lines);
        allLines.stream().forEach(line -> line.setSale(this));
        this.items.addAll(Arrays.asList(lines));
    }

    public SalesDTO toDto() {
        return SalesDTO.builder()
            .customerId(this.customer)
            .date(this.date)
            .items(this.items.stream().map(SalesLine::toDto).collect(Collectors.toList()))
            .build();
    }
}
