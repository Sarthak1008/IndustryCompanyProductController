package com.example.SpringBootAdminClientService.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@jakarta.persistence.Entity
@Data
@jakarta.persistence.Table(name="product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @jakarta.persistence.Id
    @jakarta.persistence.Column(name="productId")
    private int productId;

    @jakarta.persistence.Column(name="productName")
    private String productName;

    @jakarta.persistence.Column(name ="companyId")
    private int companyId;
    
}
