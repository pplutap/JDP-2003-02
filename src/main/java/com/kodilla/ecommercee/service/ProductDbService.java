package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDbService {
    @Autowired
    private ProductRepository productRepository;

    public void deleteById(Long id) {
        Product product = productRepository.findById(id).get();
        product.setGroup(null);
        productRepository.save(product);
        productRepository.delete(product);
    }
}
