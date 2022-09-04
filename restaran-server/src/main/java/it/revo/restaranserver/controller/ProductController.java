package it.revo.restaranserver.controller;

import it.revo.restaranserver.entity.Product;
import it.revo.restaranserver.payload.ApiResponse;
import it.revo.restaranserver.payload.ProductDto;
import it.revo.restaranserver.repository.ProductRepository;
import it.revo.restaranserver.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @PostMapping
    public HttpEntity<?> saveProduct(@RequestBody ProductDto productDto) {
        ApiResponse apiResponse = productService.addProduct(productDto);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getProduct() {
        List<Product> all = productRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/getByCategory/{id}")
    public HttpEntity<?> getProductByCategory(@PathVariable UUID id) {
        List<ProductDto> productByCategory = productService.getProductByCategory(id);
        return ResponseEntity.ok(productByCategory);
    }
}
