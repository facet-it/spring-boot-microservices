package com.lightspeed.example.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/sales")
public class SalesApi {

    @Autowired
    private SalesCrudService crudService;

    @GetMapping
    public List<SalesDTO> findAllSales() {
        return crudService.getAllSales();
    }

    @GetMapping("/create")
    public void createDummy() {
        Random randomgenerator = new Random();
        int randomNumber = randomgenerator.nextInt(5) + 1;
        Sale sale = new Sale();
        sale.setCustomer(1);
        sale.setDate(System.currentTimeMillis());

        SalesLine line1 = new SalesLine();
        line1.setAmount(randomNumber);
        line1.setProduct(randomgenerator.nextInt(7 - 1) + 1);

        SalesLine line2 = new SalesLine();
        line2.setAmount(randomNumber);
        line2.setProduct(randomgenerator.nextInt(7 - 1) + 1);

        sale.addItems(line1, line2);

        crudService.addSale(sale);
    }

}
