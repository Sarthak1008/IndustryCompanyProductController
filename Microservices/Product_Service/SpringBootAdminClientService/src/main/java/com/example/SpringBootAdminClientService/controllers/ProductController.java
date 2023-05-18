package com.example.SpringBootAdminClientService.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootAdminClientService.models.Product;
import com.example.SpringBootAdminClientService.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value="/getproducts")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(value="/getProductById/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") int productId) {
        try {
            Product product = productService.getProductById(productId);
            return new ResponseEntity<Product> (product,HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<Product> (HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/getProductByCompanyId/{companyId}")
    public ResponseEntity<List<Product>> getProductByCompanyId(@PathVariable("companyId") int companyId) {
        try {
            List<Product> products = productService.findByCompanyId(companyId);
            return new ResponseEntity<List<Product>> (products,HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<List<Product>> (HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/getProductByName/productName")
    public ResponseEntity<Product> getProductByName(@RequestParam("productName") String productName) {
        try {
            Product product = productService.getProductByName(productName);
            return new ResponseEntity<Product> (product,HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<Product> (HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping(value = "/addProduct")
    public Product addCountry(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PutMapping(value="/updateProduct/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "productId") int productId,@RequestBody Product product) {
        try {
            Product existproduct= productService.getProductById(productId);
            existproduct.setProductName(product.getProductName());
            existproduct.setCompanyId(product.getCompanyId());
            existproduct.setProductId(productId);

            Product updatedProduct = productService.updateProduct(existproduct);
            return new ResponseEntity<Product> (updatedProduct,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Product> (HttpStatus.CONFLICT);
        }
    }
    @DeleteMapping(value="/deleteProduct/{productId}")
    public String deleteProduct(@PathVariable("productId") int productId) {
        String pname = productService.getProductById(productId).getProductName();
        productService.deleteProduct(productId);
        return ("Deleted product "+pname);
    }
}
