package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
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

    private Order fillDb() {
        User user1 = new User("Mateusz", "unblocked", 3213L);
        List<Cart> cartsList = new ArrayList<>();
        Order order = new Order(user1, cartsList);

        Cart cart1 = new Cart(
                "Kurtka zimowa",
                "Pellentesque tempus interdum quam ut rhoncus. Donec ullamcorper turpis dolor.",
                new BigDecimal(100),
                1L,
                order);

        Cart cart2 = new Cart(
                "płaszcz",
                "Pellentesque tempus interdum quam ut rhoncus. Donec ullamcorper turpis dolor.",
                new BigDecimal(150),
                1L,
                order);

        Cart cart3 = new Cart(
                "buty",
                "Pellentesque tempus interdum quam ut rhoncus. Donec ullamcorper turpis dolor.",
                new BigDecimal(100),
                4L,
                order);

        cartsList.add(cart1);
        cartsList.add(cart2);
        cartsList.add(cart3);

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
        Assert.assertEquals(3, orderFromDb.get().getCarts().size());
        Assert.assertEquals("Mateusz", orderFromDb.get().getUser().getUsername());

        //Clean up
        orderRepository.deleteAll();
        userRepository.deleteAll();
        cartRepository.deleteAll();

    }
}
