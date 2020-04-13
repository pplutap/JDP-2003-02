package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

@Transactional
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

        long size = productRepository.findAll().size();

        assertTrue(actualBoots.isPresent());
        assertTrue(actualShirt.isPresent());
        Assert.assertEquals(2, size);

        //CleanUp
        productRepository.deleteAll();
    }
}



