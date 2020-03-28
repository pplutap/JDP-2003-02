package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/ecommerce")
@RestController
public class ProductController {

    @GetMapping(value = "getProducts")
    public List<ProductDto> getProducts() {
       return null;
    }

    @GetMapping(value = "getProductById")
    public ProductDto getTProductById(@RequestParam Long productId) throws ProductNotFoundException {
        return new ProductDto();
    }

    @PostMapping(value = "createProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createProduct(@RequestBody ProductDto productDto) {

    }

    @PutMapping(value = "updateProduct")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return new ProductDto();
    }

    @DeleteMapping(value = "deleteProduct")
    public void deleteProduct(@RequestParam Long productId) {

    }
}