package com.lightspeed.example.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
public class SalesCrudService {

    @Autowired
    private SalesRepository repository;

    @Autowired
    private KafkaTemplate<String, SaleCreatedEvent> template;

    public List<SalesDTO> getAllSales() {
        List<SalesDTO> sales = new LinkedList<>();
        Iterable<Sale> result = repository.findAll();

        result.forEach(sale -> {
            SalesDTO dto = sale.toDto();
            sales.add(dto);
        });

        return sales;
    }

    @Transactional
    public void addSale(Sale sale) {
        Sale result = repository.save(sale);
        SaleCreatedEvent event = new SaleCreatedEvent(result.getDate(), result.getCustomer(), result.getItems());

        template.send("sales", event);
    }

}
