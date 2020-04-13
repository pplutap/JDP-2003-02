package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
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


@RunWith(SpringRunner.class)
@SpringBootTest
public class CartRepositoryTestSuite {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupDbService groupDbService;
    @Autowired
    private ProductDbService productDbService;


    @Test
    public void testRemoveProductsFromCart() {
        //Given
        User wrobel = User.builder().username("wrobel").status(1).userKey(77L).build();
        String groupName = "dresses";
        List<Product> products = new ArrayList<>();
        Group groupDresses = new Group(null, groupName, products);
        Product product11 = Product.builder().name("P1").description("Pellentesque tempus interdum quam ut rhoncus.").price(BigDecimal.valueOf(230)).build();
        Product product21 = Product.builder().name("P2").description("Pellentesque tempus interdum quam ut rhoncus.").price(BigDecimal.valueOf(230)).build();
        Product product31 = Product.builder().name("P3").description("Pellentesque tempus interdum quam ut rhoncus.").price(BigDecimal.valueOf(230)).build();
        List<Product> cartItems = new ArrayList<>();
        cartItems.add(product11);
        cartItems.add(product21);
        cartItems.add(product31);
        Cart cartWithProducts = new Cart(null, new BigDecimal(200.0),true, wrobel, cartItems);

        //when
        userRepository.save(wrobel);
        Long wrobelId = wrobel.getId();
        groupRepository.save(groupDresses);
        Long dresses22Id = groupDresses.getId();
        productRepository.save(product11);
        Long pro11Id = product11.getId();
        productRepository.save(product21);
        Long pro21Id = product21.getId();
        productRepository.save(product31);
        Long pro31Id = product31.getId();
        cartRepository.save(cartWithProducts);


        cartWithProducts.getCartItems().remove(product11);
        cartWithProducts.getCartItems().remove(product21);
        cartWithProducts.getCartItems().remove(product31);
        cartRepository.save(cartWithProducts);
        Long cartWithProdId = cartWithProducts.getId();

        int actualCartSize = cartWithProducts.getCartItems().size();

        //Then
        Assert.assertEquals(0, actualCartSize);

        //CleanUp
        try {
            userRepository.deleteById(wrobelId);
            productDbService.deleteById(pro11Id);
            productDbService.deleteById(pro21Id);
            productDbService.deleteById(pro31Id);
            groupRepository.deleteById(dresses22Id);
            cartRepository.deleteById(cartWithProdId);
        } catch (Exception e) {
            //do nothing
        }
    }


    @Test
    public void testAddProductToCart() {
        //Given
        User kowalski = User.builder().username("kowalski").status(1).userKey(111L).build();
        String groupName = "dresses";
        List<Product> products = new ArrayList<>();
        Group groupDresses = new Group(null, groupName, products);
        Product product10 = Product.builder().name("P1").description("Pellentesque tempus interdum quam ut rhoncus.").price(BigDecimal.valueOf(230)).build();
        Product product20 = Product.builder().name("P2").description("Pellentesque tempus interdum quam ut rhoncus.").price(BigDecimal.valueOf(230)).build();
        Product product30 = Product.builder().name("P3").description("Pellentesque tempus interdum quam ut rhoncus.").price(BigDecimal.valueOf(230)).build();
        products.add(product10);
        products.add(product20);
        products.add(product30);

        List<Product> cartItems = new ArrayList<>();
        cartItems.add(product10);
        cartItems.add(product20);
        cartItems.add(product30);
        Cart cartWithProd = new Cart(null, new BigDecimal(0),true, kowalski, cartItems);

        //when
        userRepository.save(kowalski);
        Long kowalskiId = kowalski.getId();
        groupRepository.save(groupDresses);
        Long groupDressesId = groupDresses.getId();
        productRepository.save(product10);
        Long pro10Id = product10.getId();
        productRepository.save(product20);
        Long pro20Id = product20.getId();
        productRepository.save(product30);
        Long pro30Id = product30.getId();
        cartRepository.save(cartWithProd);
        Long cartWithProdId = cartWithProd.getId();

        //Then
        Assert.assertEquals(3, cartWithProd.getCartItems().size());

        //CleanUp
        try {
            productDbService.deleteById(pro10Id);
            productDbService.deleteById(pro20Id);
            productDbService.deleteById(pro30Id);
            groupRepository.deleteById(groupDressesId);
            cartRepository.deleteById(cartWithProdId);
            userRepository.deleteById(kowalskiId);
        } catch (Exception e) {
            //do nothing
        }
    }


    @Test
    public void updateUserDataWithCart() {
        //Given
        User nowak = User.builder().username("nowak").status(0).userKey(99L).build();
        String groupName = "dresses";
        List<Product> products = new ArrayList<>();
        Group groupDresses = new Group(null, groupName, products);
        Product product13 = Product.builder().name("P1").description("Pellentesque tempus interdum quam ut rhoncus.").price(BigDecimal.valueOf(230)).build();
        Product product23 = Product.builder().name("P2").description("Pellentesque tempus interdum quam ut rhoncus.").price(BigDecimal.valueOf(230)).build();
        Product product33 = Product.builder().name("P3").description("Pellentesque tempus interdum quam ut rhoncus.").price(BigDecimal.valueOf(230)).build();
        List<Product> cartItems = new ArrayList<>();
        cartItems.add(product13);
        cartItems.add(product23);
        cartItems.add(product33);
        Cart cartWithProducts = new Cart(null, new BigDecimal(20.0),true, nowak, cartItems);

        //when
        userRepository.save(nowak);
        Long nowakId = nowak.getId();
        groupRepository.save(groupDresses);
        Long dresses33Id = groupDresses.getId();
        productRepository.save(product13);
        Long pro13Id = product13.getId();
        productRepository.save(product23);
        Long pro23Id = product13.getId();
        productRepository.save(product33);
        Long pro33Id = product33.getId();
        cartRepository.save(cartWithProducts);
        Long cartWithProductsId = cartWithProducts.getId();

        nowak.setUserKey(125L);
        userRepository.save(nowak);

        Long checkUserKey = cartWithProducts.getUser().getUserKey();

        //Then
        Assert.assertEquals(125L, checkUserKey.longValue());

        //CleanUp
        try {
            userRepository.deleteById(nowakId);
            productDbService.deleteById(pro13Id);
            productDbService.deleteById(pro23Id);
            productDbService.deleteById(pro33Id);
            groupRepository.deleteById(dresses33Id);
            cartRepository.deleteById(cartWithProductsId);
        } catch (Exception e) {
            //do nothing
        }
    }
}
