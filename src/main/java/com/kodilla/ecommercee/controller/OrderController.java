package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {
    private OrderRepository orderRepository;
    private OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @GetMapping
    public List<OrderDto> get() {
        return orderMapper.mapToOrderDtoList(orderRepository.findAll());
    }

    @GetMapping("/{id}")
    public OrderDto get(@PathVariable Long id) {
        return orderMapper.mapToOrderDto(orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found")));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderRepository.deleteById(id);
    }

    @PutMapping
    public OrderDto update(@RequestBody OrderDto orderDto) {
        return orderMapper.mapToOrderDto(orderRepository.save(orderMapper.mapToOrder(orderDto)));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void create(@RequestBody OrderDto orderDto) {
        orderRepository.save(orderMapper.mapToOrder(orderDto));
    }
}
