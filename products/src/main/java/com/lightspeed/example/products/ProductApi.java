package com.lightspeed.example.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/products")
public class ProductApi {

    @Autowired
    private CrudService crudService;

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return crudService.findAllProducts();
    }

    @GetMapping("/{productId}")
    public ProductDTO getProductById(@PathVariable(name = "productId") int productId) {
        return crudService.getProductById(productId);
    }

    @GetMapping("/create")
    public void addProduct() {
        Random randomgenerator = new Random();
        int randomNumber = randomgenerator.nextInt(10000000) + 1;
        Product product = new Product();
        product.setName("product-" + randomNumber);
        product.setDescription("some description");
        product.setPlu("plu::" + randomNumber);
        product.setPrice(randomgenerator.nextDouble());
        product.setLoyaltyPoints(randomgenerator.nextInt(500) + 20);

        crudService.addProduct(product);
    }
}
