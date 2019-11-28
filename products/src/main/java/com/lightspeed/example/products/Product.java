package com.lightspeed.example.products;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "plu")
    private String plu;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "loyalty_points")
    private Integer loyaltPoints;

    public ProductDTO toDto() {
        return ProductDTO.builder()
            .name(this.name)
            .description(this.description)
            .plu(this.plu)
            .price(this.price)
            .loyaltyPoints(this.loyaltPoints)
            .build();
    }
}
