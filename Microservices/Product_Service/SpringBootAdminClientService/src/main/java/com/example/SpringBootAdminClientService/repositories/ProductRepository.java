package com.example.SpringBootAdminClientService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringBootAdminClientService.models.Product;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{

    List<Product> findByCompanyId(int companyId);
    Product findByProductId(int productId);
    Product findByProductName(String productName);
}
