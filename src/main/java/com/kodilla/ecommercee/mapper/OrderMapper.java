package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    ProductMapper productMapper;

    @Autowired
    public OrderMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public Order mapToOrder(final OrderDto orderDto) {
        return new Order(orderDto.getId(), orderDto.getCreationDate(), orderDto.getStatus(), orderDto.getUser(), productMapper.mapToProductList(orderDto.getProducts()));
    }

    public OrderDto mapToOrderDto(final Order order) {
        return new OrderDto(order.getId(), order.getCreationDate(), order.getStatus(), order.getUser(), productMapper.mapToProductDtoList(order.getProducts()));
    }

    public List<OrderDto> mapToOrderDtoList(List<Order> ordersList) {
        return ordersList.stream()
                .map(o -> new OrderDto(o.getId(), o.getCreationDate(), o.getStatus(), o.getUser(), productMapper.mapToProductDtoList(o.getProducts())))
                .collect(Collectors.toList());
    }

    public List<Order> mapToOrderList(List<OrderDto> ordersList) {
        return ordersList.stream()
                .map(o -> new Order(o.getId(), o.getCreationDate(), o.getStatus(), o.getUser(), productMapper.mapToProductList(o.getProducts())))
                .collect(Collectors.toList());
    }
}
