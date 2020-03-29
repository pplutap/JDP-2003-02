package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/v1/ecommerce")
@RestController
public class ProductController {

    @GetMapping(value = "getProducts")
    public List<ProductDto> getProducts() {
        return getMockProductList();
    }

    @GetMapping(value = "getProductById")
    public ProductDto getTProductById(@RequestParam Long productId) throws ProductNotFoundException {
        if (productId.equals(1L)) {
            return new ProductDto(
                    1L,
                    "Kurtka zimowa",
                    "Pellentesque tempus interdum quam ut rhoncus. Donec ullamcorper turpis dolor. " +
                            "Donec euismod pretium eros et eleifend. Aliquam vulputate faucibus lorem non auctor. " +
                            "Vivamus erat turpis, molestie a nisl non, scelerisque luctus enim. " +
                            "Nunc mi mi, laoreet ac mollis nec, pharetra sit amet tortor. Vivamus a bibendum purus.",
                    new BigDecimal(100),
                    "1");
        } else if (productId.equals(2L)) {
            return new ProductDto(
                    2L,
                    "płaszcz",
                    "Pellentesque tempus interdum quam ut rhoncus. Donec ullamcorper turpis dolor. " +
                            "Donec euismod pretium eros et eleifend. Aliquam vulputate faucibus lorem non auctor. " +
                            "Vivamus erat turpis, molestie a nisl non, scelerisque luctus enim. " +
                            "Nunc mi mi, laoreet ac mollis nec, pharetra sit amet tortor. Vivamus a bibendum purus.",
                    new BigDecimal(150),
                    "1");
        } else {
            throw new ProductNotFoundException();
        }
    }

    @PostMapping(value = "createProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createProduct(@RequestBody ProductDto productDto) {

    }

    @PutMapping(value = "updateProduct")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return new ProductDto(
                1L,
                "Kurtka zimowa",
                "Pellentesque tempus interdum quam ut rhoncus. Donec ullamcorper turpis dolor. " +
                        "Donec euismod pretium eros et eleifend. Aliquam vulputate faucibus lorem non auctor. " +
                        "Vivamus erat turpis, molestie a nisl non, scelerisque luctus enim. " +
                        "Nunc mi mi, laoreet ac mollis nec, pharetra sit amet tortor. Vivamus a bibendum purus.",
                new BigDecimal(100),
                "1");
    }

    @DeleteMapping(value = "deleteProduct")
    public void deleteProduct(@RequestParam Long productId) {

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
                "1");
        ProductDto productDto2 = new ProductDto(
                2L,
                "płaszcz",
                "Pellentesque tempus interdum quam ut rhoncus. Donec ullamcorper turpis dolor. " +
                        "Donec euismod pretium eros et eleifend. Aliquam vulputate faucibus lorem non auctor. " +
                        "Vivamus erat turpis, molestie a nisl non, scelerisque luctus enim. " +
                        "Nunc mi mi, laoreet ac mollis nec, pharetra sit amet tortor. Vivamus a bibendum purus.",
                new BigDecimal(150),
                "1");

        ProductDto productDto3 = new ProductDto(
                3L,
                "buty",
                "Pellentesque tempus interdum quam ut rhoncus. Donec ullamcorper turpis dolor. " +
                        "Donec euismod pretium eros et eleifend. Aliquam vulputate faucibus lorem non auctor. " +
                        "Vivamus erat turpis, molestie a nisl non, scelerisque luctus enim. " +
                        "Nunc mi mi, laoreet ac mollis nec, pharetra sit amet tortor. Vivamus a bibendum purus.",
                new BigDecimal(100),
                "4");

        productsList.add(productDto1);
        productsList.add(productDto2);
        productsList.add(productDto3);

        return productsList;
    }
}