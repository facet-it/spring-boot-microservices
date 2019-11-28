package com.lightspeed.example.sales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "saleslines")
@Data
public class SalesLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sales_id")
    private Sale sale;

    @Column(name = "product_id")
    private Integer product;

    @Column(name = "amount")
    private Integer amount;

    public SalesLineDTO toDto() {
        return SalesLineDTO.builder()
            .salesId(this.sale.getId())
            .productId(this.product)
            .amount(this.amount)
            .build();
    }

}
