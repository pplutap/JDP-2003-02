package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public Order mapToOrder(final OrderDto orderDto) {
        return new Order(
                orderDto.getOrderId(),
                orderDto.getUserId()
        );
    }

    public OrderDto mapToOrderDto(final Order order) {
        return new OrderDto(
                order.getOrderId(),
                order.getUserId()
        );
    }

    public List<OrderDto> mapToOrderDtoList(final List<Order> orderLists) {
        return orderLists.stream()
                .map(orders -> new OrderDto(orders.getOrderId(), orders.getUserId()))
                .collect(Collectors.toList());
    }
}
