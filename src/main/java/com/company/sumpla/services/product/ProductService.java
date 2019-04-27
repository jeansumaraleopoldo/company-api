package com.company.sumpla.services.product;

import com.company.sumpla.model.Product;

import java.util.List;

public interface ProductService {

    public List<Product> getProducts();

    public Product findById(Long id);

    public Product saveProduct(Product product);

    public void deleteById(Long idProduct);
}
