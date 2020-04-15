package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.service.OrderDbService;
import com.kodilla.ecommercee.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    ProductMapper productMapper;
    UserDbService userDbService;

    @Autowired
    public OrderMapper(ProductMapper productMapper, UserDbService userDbService) {
        this.productMapper = productMapper;
        this.userDbService = userDbService;
    }

    public Order mapToOrder(final OrderDto orderDto) {
        return new Order(orderDto.getId(), orderDto.getCreationDate(), orderDto.getStatus(), userDbService.getUserById(orderDto.getUserId()).get(), productMapper.mapToProductList(orderDto.getProducts()));
    }

    public OrderDto mapToOrderDto(final Order order) {
        return new OrderDto(order.getId(), order.getCreationDate(), order.getStatus(), order.getUser().getId(), productMapper.mapToProductDtoList(order.getProducts()));
    }

    public List<OrderDto> mapToOrderDtoList(List<Order> ordersList) {
        return ordersList.stream()
                .map(o -> new OrderDto(o.getId(), o.getCreationDate(), o.getStatus(), o.getUser().getId(), productMapper.mapToProductDtoList(o.getProducts())))
                .collect(Collectors.toList());
    }

    public List<Order> mapToOrderList(List<OrderDto> ordersList) {
        return ordersList.stream()
                .map(o -> new Order(o.getId(), o.getCreationDate(), o.getStatus(), userDbService.getUserById(o.getUserId()).get(), productMapper.mapToProductList(o.getProducts())))
                .collect(Collectors.toList());
    }
}
