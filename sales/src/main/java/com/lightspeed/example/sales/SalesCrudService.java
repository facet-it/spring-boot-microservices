package com.lightspeed.example.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SalesCrudService {

    @Autowired
    private SalesRepository repository;

    @Autowired
    private KafkaTemplate<String, String> template;

    public List<SalesDTO> getAllSales() {
        List<SalesDTO> sales = new LinkedList<>();
        Iterable<Sale> result = repository.findAll();

        result.forEach(sale -> {
            SalesDTO dto = sale.toDto();
            sales.add(dto);
        });

        return sales;
    }

    public void addSale(Sale sale) {
        Sale result = repository.save(sale);
        template.send("sales", "Sale created with id " + result.getId());
    }

}
