package com.example.SpringBootAdminClientService.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringBootAdminClientService.models.Product;
import com.example.SpringBootAdminClientService.repositories.ProductRepository;
import com.example.SpringBootAdminClientService.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByCompanyId(int companyId) {
        return productRepository.findByCompanyId(companyId);
    }

    @Override
    public Product getProductById(int productId) {
        return productRepository.findByProductId(productId);
    }

    @Override
    public Product getProductByName(String productName) {
        return productRepository.findByProductName(productName);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public String deleteProduct(int productId) {
        Product product = productRepository.getReferenceById(productId);
        String name = "Deleted product "+product.getProductName();
        productRepository.delete(product);
        return name;
    }
    
}
