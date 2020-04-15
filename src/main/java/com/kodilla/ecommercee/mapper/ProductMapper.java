package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    OrderMapper orderMapper;
    GroupRepository groupRepository;

    @Autowired
    public ProductMapper(OrderMapper orderMapper, GroupRepository groupRepository) {
        this.orderMapper = orderMapper;
        this.groupRepository = groupRepository;
    }

    public Product mapToProduct(final ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getName(), productDto.getDescription(), productDto.getPrice(), groupRepository.findById(Long.parseLong(productDto.getGroupId())).get() , orderMapper.mapToOrderList(productDto.getOrders()));
    }

    public ProductDto mapToProductDto(final Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), Long.toString(product.getGroup().getId()), orderMapper.mapToOrderDtoList(product.getOrders()));
    }

    public List<ProductDto> mapToProductDtoList(List<Product> productList) {
        return productList.stream()
                .map(p -> new ProductDto(p.getId(), p.getName(), p.getDescription(), p.getPrice(), Long.toString(p.getGroup().getId()), orderMapper.mapToOrderDtoList(p.getOrders())))
                .collect(Collectors.toList());
    }

    public List<Product> mapToProductList(List<ProductDto> productList) {
        return productList.stream()
                .map(p -> new Product(p.getId(), p.getName(), p.getDescription(), p.getPrice(), groupRepository.findById(Long.parseLong(p.getGroupId())).get(), orderMapper.mapToOrderList(p.getOrders())))
                .collect(Collectors.toList());
    }
}
