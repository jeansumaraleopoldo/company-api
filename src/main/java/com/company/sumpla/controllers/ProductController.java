package com.company.sumpla.controllers;

import com.company.sumpla.dtos.ProductDto;
import com.company.sumpla.model.Product;
import com.company.sumpla.services.product.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {
    
    private final ProductService productService;
    private final ProductDto.RepresentationBuilder productRB;

    public ProductController(ProductService productService, ProductDto.RepresentationBuilder productRB) {
        this.productService = productService;
        this.productRB = productRB;
    }

    @GetMapping("products")
    public ResponseEntity getClients(){
        List<ProductDto> products = productService.getProducts().stream()
                .map(productRB::toRepresentation)
                .collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    @GetMapping("products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        Product product = productService.findById(id);
        ProductDto providerDto = productRB.toRepresentation(product);
        return ResponseEntity.ok(providerDto);
    }

    @PostMapping("products")
    public ResponseEntity addProduct(@RequestBody ProductDto representation) {
        Product fromRepresentation = productRB.fromRepresentation(representation, Product.Builder.create());
        Product savedProduct = productService.saveProduct(fromRepresentation);

        final URI location = ServletUriComponentsBuilder
                .fromCurrentServletMapping().path("/products/{id}").build()
                .expand(savedProduct.getId()).toUri();

        final HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("products/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto representation) {
        Product product = productService.findById(id);
        Product.Builder builder = Product.Builder.from(product);
        Product fromRepresentation = productRB.fromRepresentation(representation, builder);
        Product savedProduct = productService.saveProduct(fromRepresentation);
        ProductDto providerDto = productRB.toRepresentation(savedProduct);
        return ResponseEntity.ok(providerDto);
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
