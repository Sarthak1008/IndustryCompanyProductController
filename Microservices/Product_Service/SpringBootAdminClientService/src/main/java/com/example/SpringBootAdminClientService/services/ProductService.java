package com.example.SpringBootAdminClientService.services;

import java.util.List;

import com.example.SpringBootAdminClientService.models.Product;

public interface ProductService {
    List<Product> getAllProducts();
    List<Product> findByCompanyId(int companyId);
    Product getProductById(int productId);
    Product getProductByName(String productName);
    Product addProduct(Product product);
    Product updateProduct(Product product);
    String deleteProduct(int productId);
}
