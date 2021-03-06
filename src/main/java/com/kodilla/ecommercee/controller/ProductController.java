package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @GetMapping
    public List<ProductDto> get() {
        return getMockProductList();
    }

    @GetMapping("/{id}")
    public ProductDto get(@PathVariable Long id) throws ProductNotFoundException {
        return new ProductDto(
                1L,
                "Kurtka zimowa",
                "Pellentesque tempus interdum quam ut rhoncus. Donec ullamcorper turpis dolor. " +
                        "Donec euismod pretium eros et eleifend. Aliquam vulputate faucibus lorem non auctor. " +
                        "Vivamus erat turpis, molestie a nisl non, scelerisque luctus enim. " +
                        "Nunc mi mi, laoreet ac mollis nec, pharetra sit amet tortor. Vivamus a bibendum purus.",
                new BigDecimal(100),
                1L);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody ProductDto productDto) {

    }

    @PutMapping
    public ProductDto update(@RequestBody ProductDto productDto) {
        return new ProductDto(
                1L,
                "Kurtka zimowa",
                "Pellentesque tempus interdum quam ut rhoncus. Donec ullamcorper turpis dolor. " +
                        "Donec euismod pretium eros et eleifend. Aliquam vulputate faucibus lorem non auctor. " +
                        "Vivamus erat turpis, molestie a nisl non, scelerisque luctus enim. " +
                        "Nunc mi mi, laoreet ac mollis nec, pharetra sit amet tortor. Vivamus a bibendum purus.",
                new BigDecimal(100),
                1L);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

    }

    private List<ProductDto> getMockProductList() {
        ArrayList<ProductDto> productsList = new ArrayList<>();

        ProductDto productDto1 = new ProductDto(
                1L,
                "Kurtka zimowa",
                "Pellentesque tempus interdum quam ut rhoncus. Donec ullamcorper turpis dolor. " +
                        "Donec euismod pretium eros et eleifend. Aliquam vulputate faucibus lorem non auctor. " +
                        "Vivamus erat turpis, molestie a nisl non, scelerisque luctus enim. " +
                        "Nunc mi mi, laoreet ac mollis nec, pharetra sit amet tortor. Vivamus a bibendum purus.",
                new BigDecimal(100),
                1L);
        ProductDto productDto2 = new ProductDto(
                2L,
                "płaszcz",
                "Pellentesque tempus interdum quam ut rhoncus. Donec ullamcorper turpis dolor. " +
                        "Donec euismod pretium eros et eleifend. Aliquam vulputate faucibus lorem non auctor. " +
                        "Vivamus erat turpis, molestie a nisl non, scelerisque luctus enim. " +
                        "Nunc mi mi, laoreet ac mollis nec, pharetra sit amet tortor. Vivamus a bibendum purus.",
                new BigDecimal(150),
                1L);

        ProductDto productDto3 = new ProductDto(
                3L,
                "buty",
                "Pellentesque tempus interdum quam ut rhoncus. Donec ullamcorper turpis dolor. " +
                        "Donec euismod pretium eros et eleifend. Aliquam vulputate faucibus lorem non auctor. " +
                        "Vivamus erat turpis, molestie a nisl non, scelerisque luctus enim. " +
                        "Nunc mi mi, laoreet ac mollis nec, pharetra sit amet tortor. Vivamus a bibendum purus.",
                new BigDecimal(100),
                4L);

        productsList.add(productDto1);
        productsList.add(productDto2);
        productsList.add(productDto3);

        return productsList;
    }
}
