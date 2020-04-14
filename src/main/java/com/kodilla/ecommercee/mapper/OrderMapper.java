package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    public Order mapToOrder(final OrderDto orderDto) {
        return new Order(orderDto.getId(), orderDto.getCreationDate(), orderDto.getStatus(), orderDto.getUser(), orderDto.getProductsList());
    }

    public OrderDto mapToOrderDto(final Order order) {
        return new OrderDto(order.getId(), order.getCreationDate(), order.getStatus(),order.getUser(), order.getProducts());
    }

    public List<OrderDto> mapToOrderDtoList(List<Order> ordersList) {
        return ordersList.stream()
                .map(o -> new OrderDto(o.getId(), o.getCreationDate(), o.getStatus(), o.getUser(), o.getProducts()))
                .collect(Collectors.toList());
    }
}
