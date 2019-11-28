package com.lightspeed.example.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SalesCrudService {

    @Autowired
    private SalesRepository repository;

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
        repository.save(sale);
    }

}
