package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {
    ProductMapper productMapper;
    UserDbService userDbService;

    @Autowired
    public CartMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public Cart mapToCart(final CartDto cartDto) {
        return new Cart(cartDto.getId(), cartDto.getTotalPrice(), cartDto.isClosed(), userDbService.getUserById(cartDto.getUserId()).get(), productMapper.mapToProductList(cartDto.getCartItems()));
    }

    public CartDto mapToCartDto(final Cart cart) {
        return new CartDto(cart.getId(), cart.getTotalPrice(), cart.isClosed(), cart.getUser().getId(), productMapper.mapToProductDtoList(cart.getCartItems()));
    }

    public List<CartDto> mapToCartDtoList(List<Cart> cartsList) {
        return cartsList.stream()
                .map(c -> new CartDto(c.getId(), c.getTotalPrice(), c.isClosed(), c.getUser().getId(), productMapper.mapToProductDtoList(c.getCartItems())))
                .collect(Collectors.toList());
    }

    public List<Cart> mapToCartList(List<CartDto> cartsList) {
        return cartsList.stream()
                .map(c -> new Cart(c.getId(), c.getTotalPrice(), c.isClosed(), userDbService.getUserById(c.getUserId()).get(), productMapper.mapToProductList(c.getCartItems())))
                .collect(Collectors.toList());
    }
}
