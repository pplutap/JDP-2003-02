package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    OrderMapper orderMapper;

    @Autowired
    public ProductMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public Product mapToProduct(final ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getName(), productDto.getDescription(), productDto.getPrice(), productDto.getGroup(), orderMapper.mapToOrderList(productDto.getOrders()));
    }

    public ProductDto mapToProductDto(final Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getGroup(), orderMapper.mapToOrderDtoList(product.getOrders()));
    }

    public List<ProductDto> mapToProductDtoList(List<Product> productList) {
        return productList.stream()
                .map(p -> new ProductDto(p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getGroup(), orderMapper.mapToOrderDtoList(p.getOrders())))
                .collect(Collectors.toList());
    }

    public List<Product> mapToProductList(List<ProductDto> productList) {
        return productList.stream()
                .map(p -> new Product(p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getGroup(), orderMapper.mapToOrderList(p.getOrders())))
                .collect(Collectors.toList());
    }
}
