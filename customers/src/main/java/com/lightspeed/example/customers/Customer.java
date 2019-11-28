package com.lightspeed.example.customers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "loyalty_amount")
    private Integer loyaltyAmount;

    public CustomerDTO toDto() {
        return CustomerDTO.builder()
            .fullName(this.fullName)
            .email(this.email)
            .loyaltyAmount(this.loyaltyAmount)
            .build();
    }

    public boolean canBuyWithLoyalty(Integer loyaltyCost) {
        if (loyaltyCost != null && loyaltyCost < this.loyaltyAmount) {
            return true;
        }
        return false;
    }
}
