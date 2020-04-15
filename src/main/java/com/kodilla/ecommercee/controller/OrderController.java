package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.service.OrderDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {
    private OrderDbService orderDbService;
    private OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderDbService orderDbService, OrderMapper orderMapper) {
        this.orderDbService = orderDbService;
        this.orderMapper = orderMapper;
    }

    @GetMapping
    public List<OrderDto> get() {
        return orderMapper.mapToOrderDtoList(orderDbService.getAllOrders());
    }

    @GetMapping("/{id}")
    public OrderDto get(@PathVariable Long id) {
        return orderMapper.mapToOrderDto(orderDbService.getOrderById(id).orElseThrow(() -> new RuntimeException("Order not found")));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderDbService.deleteOrderById(id);
    }

    @PutMapping
    public OrderDto update(@RequestBody OrderDto orderDto) {
        return orderMapper.mapToOrderDto(orderDbService.saveOrder(orderMapper.mapToOrder(orderDto)));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void create(@RequestBody OrderDto orderDto) {
        orderDbService.saveOrder(orderMapper.mapToOrder(orderDto));
    }
}
