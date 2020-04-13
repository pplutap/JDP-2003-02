package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.service.GroupDbService;
import com.kodilla.ecommercee.service.ProductDbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTestSuite {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSaveProduct() {
        //Given
        Product boots = Product.builder().name("Boots").description("Leather").price(new BigDecimal("43.2")).build();
        Product shirt = Product.builder().name("Shirt").description("White").price(new BigDecimal("28.2")).build();

        //When

        productRepository.save(boots);
        productRepository.save(shirt);

        //Then

        Long bootsId = boots.getId();
        Long shirtId = shirt.getId();

        Optional<Product> actualBoots = productRepository.findById(bootsId);
        Optional<Product> actualShirt = productRepository.findById(shirtId);

        int listSize = productRepository.findAll().size();

        assertTrue(actualBoots.isPresent());
        assertTrue(actualShirt.isPresent());
        assertEquals(2, listSize);

        //CleanUp
        productRepository.deleteAll();
    }
}



