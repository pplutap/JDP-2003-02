package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
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
public class OrderRepositoryTestSuit {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    GroupRepository groupRepository;

    private Order fillDb() {
        User user1 = new User("Mateusz", "unblocked", 3213L);
        List<Product> productsList = new ArrayList<>();
        Group ubrania = new Group("Ubrania", productsList);
        Group obuwie = new Group("Obuwie", productsList);

        Product product1 = new Product(
                "Kurtka zimowa",
                "Pellentesque tempus interdum quam ut rhoncus. Donec ullamcorper turpis dolor.",
                new BigDecimal(100),
                ubrania);

        Product product2 = new Product(
                "płaszcz",
                "Pellentesque tempus interdum quam ut rhoncus. Donec ullamcorper turpis dolor.",
                new BigDecimal(150),
                ubrania);

        Product product3 = new Product(
                "buty",
                "Pellentesque tempus interdum quam ut rhoncus. Donec ullamcorper turpis dolor.",
                new BigDecimal(100),
                obuwie);

        productsList.add(product1);
        productsList.add(product2);
        productsList.add(product3);

        Order order = new Order(user1, productsList);

        orderRepository.save(order);
        return order;
    }

    @Test
    public void addOrderTest() {
        //Given
        Order order = fillDb();

        //When
        Long id = order.getId();
        Optional<Order> orderFromDb = orderRepository.findById(id);

        //Then
        Assert.assertEquals(3, orderFromDb.get().getProducts().size());
        Assert.assertEquals("Mateusz", orderFromDb.get().getUser().getUsername());

        //Clean up
        orderRepository.deleteAll();
        userRepository.deleteAll();
        cartRepository.deleteAll();
        groupRepository.deleteAll();

    }
}
