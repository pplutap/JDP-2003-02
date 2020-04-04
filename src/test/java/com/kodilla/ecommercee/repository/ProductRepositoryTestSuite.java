package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.service.GroupDbService;
import com.kodilla.ecommercee.service.ProductDbService;
import org.junit.Before;
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
    private ProductDbService productDbService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupDbService groupDbService;

    private Group clothes;
    private Product trousers;
    private Product boots;
    private Product shirt;


    private void createData() {
        List<Product> productList = new ArrayList<>();
        clothes = new Group(null, "clothes", productList);
        trousers = new Product(null, "Trousers", "Denim", new BigDecimal(31), clothes);
        boots = new Product(null, "Boots", "Leather", new BigDecimal("43.2"), clothes);
        shirt = new Product(null, "shirt", "White", new BigDecimal("43.2"), clothes);
        productList.add(trousers);
        productList.add(boots);
        productList.add(shirt);
    }


    @Test
    public void testGetProduct() {
        //Given
        createData();

        //When
        groupRepository.save(clothes);
        productRepository.save(trousers);
        productRepository.save(boots);
        productRepository.save(shirt);

        //Then
        Long trousersId = trousers.getId();
        Long bootsId = boots.getId();
        Long shirtId = shirt.getId();
        Long clothesId = clothes.getId();
        Optional<Product> actualTrousers = productRepository.findById(trousersId);
        Optional<Product> actualBoots = productRepository.findById(bootsId);
        Optional<Product> actualShirt = productRepository.findById(shirtId);

        assertTrue(actualTrousers.isPresent());
        assertTrue(actualBoots.isPresent());
        assertTrue(actualShirt.isPresent());

        //CleanUp
        productDbService.deleteById(trousersId);
        productDbService.deleteById(bootsId);
        productDbService.deleteById(shirtId);
        groupDbService.deleteById(clothesId);
    }

    @Test
    public void testDeleteProduct() {
        //Given
        createData();
        //When
        groupRepository.save(clothes);
        productRepository.save(trousers);
        productRepository.save(boots);
        productRepository.save(shirt);
        //Then
        Long trousersId = trousers.getId();
        Long bootsId = boots.getId();
        Long shirtId = shirt.getId();
        Long clothesId = clothes.getId();
        Optional<Product> actualTrousers = productRepository.findById(trousersId);
        Optional<Product> actualBoots = productRepository.findById(bootsId);
        Optional<Product> actualShirt = productRepository.findById(shirtId);
        Optional<Group> actualClothes = groupRepository.findById(clothesId);

        int sizeOfGroup = actualClothes.orElse(new Group()).getProducts().size();
        productDbService.deleteById(trousersId);

        actualClothes = groupRepository.findById(clothesId);
        int sizeOfGroupAfterDeleting = actualClothes.orElse(new Group()).getProducts().size();

        assertTrue(actualTrousers.isPresent());
        assertTrue(actualBoots.isPresent());
        assertTrue(actualShirt.isPresent());
        assertEquals(3, sizeOfGroup);
        assertEquals(2, sizeOfGroupAfterDeleting);

        //CleanUp
        productDbService.deleteById(bootsId);
        productDbService.deleteById(shirtId);
        groupDbService.deleteById(clothesId);
    }

    @Test
    public void testProductUpdate() {
        //Given
        createData();

        //When
        groupRepository.save(clothes);
        productRepository.save(trousers);
        productRepository.save(boots);
        productRepository.save(shirt);

        //Then
        Long trousersId = trousers.getId();
        Long bootsId = boots.getId();
        Long shirtId = shirt.getId();
        Long clothesId = clothes.getId();

        Optional<Product> actualTrousers = productRepository.findById(trousersId);
        Optional<Product> actualBoots = productRepository.findById(bootsId);
        Optional<Product> actualShirt = productRepository.findById(shirtId);

        String newTrousersDescription = "Best one";
        String newBootsDescription = "size 38";
        BigDecimal newPriceOfShirt = new BigDecimal("56.4");

        actualTrousers.orElse(new Product()).setDescription(newTrousersDescription);
        productRepository.save(actualTrousers.orElse(new Product()));
        actualBoots.orElse(new Product()).setDescription(newBootsDescription);
        productRepository.save(actualBoots.orElse(new Product()));
        actualShirt.orElse(new Product()).setPrice(newPriceOfShirt);
        productRepository.save(actualShirt.orElse(new Product()));

        assertTrue(actualTrousers.isPresent());
        assertTrue(actualBoots.isPresent());
        assertTrue(actualShirt.isPresent());
        assertEquals(newTrousersDescription, actualTrousers.get().getDescription());
        assertEquals(newBootsDescription, actualBoots.get().getDescription());
        assertEquals(newPriceOfShirt, actualShirt.get().getPrice());

        //CleanUp
        productDbService.deleteById(trousersId);
        productDbService.deleteById(bootsId);
        productDbService.deleteById(shirtId);
        groupDbService.deleteById(clothesId);
    }
}
