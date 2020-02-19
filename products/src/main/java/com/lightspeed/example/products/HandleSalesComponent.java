package com.lightspeed.example.products;

import com.lightspeed.example.sales.SaleCreatedEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.java.Log;

@Component
@Log
public class HandleSalesComponent {

    @Autowired
    private ProductRepository repository;

    @KafkaListener(topics = "sales", groupId = "handling-sales")
    public void consumeSalesEvent(SaleCreatedEvent event) {
        log.info("Handling incoming sale");
        List<Integer> productIds = event.getLines().stream()
            .map(SaleCreatedEvent.Line::getProductId)
            .collect(Collectors.toList());
        int totalLoyaltyPoints = repository.getTotalLoyalty(productIds);
        log.info("Amount of loyalty for this sale: " + totalLoyaltyPoints);
    }

}
