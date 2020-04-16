package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.service.ProductDbService;
import com.kodilla.ecommercee.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    ProductDbService productDbService;
    ProductMapper productMapper;
    UserDbService userDbService;

    @Autowired
    public OrderMapper(ProductDbService productDbService, ProductMapper productMapper, UserDbService userDbService) {
        this.productDbService = productDbService;
        this.userDbService = userDbService;
        this.productMapper = productMapper;
    }

    public Order mapToOrder(final OrderDto orderDto) {
        return new Order(orderDto.getId(), orderDto.getCreationDate(), orderDto.getStatus(), userDbService.getUserById(orderDto.getUserId()).get(), productMapper.mapToProductList(orderDto.getProducts()));
    }

    public OrderDto mapToOrderDto(final Order order) {
        return new OrderDto(order.getId(), order.getCreationDate(), order.getStatus(), order.getUser().getId(), productMapper.mapToProductDtoList(order.getProducts()));
    }

    public List<OrderDto> mapToOrderDtoList(List<Order> orderList) {
        return orderList.stream()
                .map(o -> new OrderDto(o.getId(), o.getCreationDate(), o.getStatus(), o.getUser().getId(), productMapper.mapToProductDtoList(o.getProducts())))
                .collect(Collectors.toList());
    }
}
