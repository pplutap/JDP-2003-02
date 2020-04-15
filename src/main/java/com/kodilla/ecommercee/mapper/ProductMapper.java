package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.service.GroupDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    OrderMapper orderMapper;
    CartMapper cartMapper;
    GroupDbService groupDbService;


    @Autowired
    public ProductMapper(OrderMapper orderMapper, CartMapper cartMapper, GroupDbService groupDbService) {
        this.orderMapper = orderMapper;
        this.cartMapper = cartMapper;
        this.groupDbService = groupDbService;
    }

    public Product mapToProduct(final ProductDto productDto) {
        return new Product(productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                groupDbService.getGroup(productDto.getGroupId()).get(),
                orderMapper.mapToOrderList(productDto.getOrders()),
                cartMapper.mapToCartList(productDto.getCarts()));
    }

    public ProductDto mapToProductDto(final Product product) {
        return new ProductDto(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getGroup().getId(),
                orderMapper.mapToOrderDtoList(product.getOrders()),
                cartMapper.mapToCartDtoList(product.getCarts()));
    }

    public List<ProductDto> mapToProductDtoList(List<Product> productList) {
        return productList.stream()
                .map(p -> new ProductDto(p.getId(),
                        p.getName(),
                        p.getDescription(),
                        p.getPrice(),
                        p.getGroup().getId(),
                        orderMapper.mapToOrderDtoList(p.getOrders()),
                        cartMapper.mapToCartDtoList(p.getCarts())))
                .collect(Collectors.toList());
    }

    public List<Product> mapToProductList(List<ProductDto> productList) {
        return productList.stream()
                .map(p -> new Product(p.getId(),
                        p.getName(),
                        p.getDescription(),
                        p.getPrice(),
                        groupDbService.getGroup(p.getGroupId()).get(),
                        orderMapper.mapToOrderList(p.getOrders()),
                        cartMapper.mapToCartList(p.getCarts())))
                .collect(Collectors.toList());
    }
}
