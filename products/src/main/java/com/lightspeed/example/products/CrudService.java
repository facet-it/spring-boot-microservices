package com.lightspeed.example.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import lombok.extern.java.Log;

@Service
@Log
public class CrudService {

    @Autowired
    private ProductRepository repository;

    public ProductDTO getProductById(int id) {
        Optional<Product> result = repository.findById(id);

        if(result.isPresent()) {
            return result.get().toDto();
        }
        else {
            throw new IllegalArgumentException("No product found with id " + id);
        }
    }

    public List<ProductDTO> findAllProducts() {
        List<ProductDTO> products = new LinkedList<>();
        Iterable<Product> result = repository.findAll();

        result.forEach( product -> {
            ProductDTO productDTO = product.toDto();
            products.add(productDTO);
        });

        return products;
    }

    public void addProduct(Product product) {
        Product savedProduct = repository.save(product);
        log.info("Product saved with id " + savedProduct.getId());
    }

    public void updateProduct(Product product) {
        repository.save(product);
    }

    public void deleteProduct(Product product) {
        repository.delete(product);
    }
}
