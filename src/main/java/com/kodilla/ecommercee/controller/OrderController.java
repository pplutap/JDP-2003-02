package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.OrderDto;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {
    @GetMapping
    public List<OrderDto> get() {
        return Arrays.asList(new OrderDto(1L, 0L), new OrderDto(2L, 1L));
    }

    @GetMapping("/{id}")
    public OrderDto get(@PathVariable Long orderId) {
        return new OrderDto(1L, 0L);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long orderId) {
    }

    @PutMapping
    public OrderDto update(@RequestBody OrderDto orderDto) {
        return new OrderDto(2L, 1L);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void create(@RequestBody OrderDto orderDto) {
    }
}
