package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.service.GroupDbService;
import com.kodilla.ecommercee.service.ProductDbService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupRepositoryTestSuite {
    @Autowired
    private GroupRepository groupRepository;

    private Group getTestGroup() {
        String name = "dresses";


        List<Product> products = new ArrayList<>();

        Product product1 = Product.builder().name("Boots").description("Leather1").price(new BigDecimal("43.2")).build();
        Product product2 = Product.builder().name("Boots1").description("Leather2").price(new BigDecimal("43.2")).build();
        Product product3 = Product.builder().name("Boots2").description("Leather3").price(new BigDecimal("43.2")).build();
        products.add(product1);
        products.add(product2);
        products.add(product3);

        System.out.println(products.size());

        Group group = new Group(null, name, products);
        groupRepository.save(group);

        return group;

    }

    @Test
    public void testSaveGroupWithProducts() {
        //Given
        Group group = getTestGroup();
        //When

        //Then
        Long groupId = group.getId();
        Optional<Group> actualGroup = groupRepository.findById(groupId);
        Assert.assertTrue(actualGroup.isPresent());
        Assert.assertEquals("dresses", actualGroup.get().getName());
        Assert.assertEquals(3, group.getProducts().size());

        //CleanUp
        groupRepository.deleteAll();
    }

    @Test
    public void testUpdateGroup() {
        //Given
        Group group = getTestGroup();
        String newGroupName = "Clothes";

        //When
        groupRepository.save(group);
        group.setName(newGroupName);
        groupRepository.save(group);

        //Then
        Long groupId = group.getId();
        Optional<Group> actualGroup = groupRepository.findById(groupId);
        Assert.assertTrue(actualGroup.isPresent());
        Assert.assertEquals(newGroupName, actualGroup.get().getName());

        //CleanUp
        groupRepository.deleteAll();
    }
}

