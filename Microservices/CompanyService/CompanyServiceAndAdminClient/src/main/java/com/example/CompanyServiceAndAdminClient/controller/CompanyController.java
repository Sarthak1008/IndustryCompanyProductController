package com.example.CompanyServiceAndAdminClient.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
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

import com.example.CompanyServiceAndAdminClient.entities.Company;
import com.example.CompanyServiceAndAdminClient.services.CompanyService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

    @GetMapping(value="/getCompanies")
    public List<Company> getAllCompanies() {
        return companyService.getAllCompany();
    }

    @GetMapping(value="/getAllCompaniesAllProductsOfIndustry/{industryId}")
    public List<Company> getAllCompaniesAllProducts(@PathVariable("industryId") int industryId) {

        return companyService.getAllCompaniesAllProducts(industryId);
    
    }

    

    @GetMapping(value="/getCompanyById/{companyId}")
    public ResponseEntity<Company> getCompanyById(@PathVariable("companyId") int companyId) {
        try {
            Company company = companyService.findByCompanyId(companyId);
            return new ResponseEntity<Company> (company,HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<Company> (HttpStatus.NOT_FOUND);
        }
    }

    int retryCount = 1;

    @GetMapping(value="/getProductByCompanyId/{companyId}")
    @CircuitBreaker(name="CompanyBreaker",fallbackMethod = "CompanyFallback")
    @Retry(name="CompanyBreaker",fallbackMethod = "CompanyFallback")
    @RateLimiter(name = "CompanyBreaker",fallbackMethod = "CompanyFallback")
    public ResponseEntity<Company> getProductByCompanyId(@PathVariable("companyId") int companyId) {
        logger.info("Get Single User Handler: CompanyController");
            Company company = companyService.findProductByCompanyId(companyId);
            return new ResponseEntity<Company> (company,HttpStatus.OK);
    }

    public ResponseEntity<Company> CompanyFallback(int companyId,Exception ex){
        logger.info("Fallback is executed because service is down: ",ex.getMessage());
        logger.info("Retry Count: {} ",retryCount);
        retryCount++;
        Company company = Company.builder().companyId(0).companyName("dummy").industryTypeId(0).build();
        return new ResponseEntity<Company>(company,HttpStatus.OK);
    }

    @GetMapping(value="/getCompanyByName/companyName")
    public ResponseEntity<Company> getCompanyByName(@RequestParam("companyName") String companyName) {
        try {
            Company company = companyService.getCompanyByName(companyName);
            return new ResponseEntity<Company> (company,HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<Company> (HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping(value = "/addCompany")
    public Company addCompany(@RequestBody Company company){
        return companyService.addCompany(company);
    }

    @PutMapping(value="/updateCompany/{companyId}")
    public ResponseEntity<Company> updateProduct(@PathVariable(value = "companyId") int companyId,@RequestBody Company company) {
        try {
            Company existcompany = companyService.findByCompanyId(companyId);
            existcompany.setCompanyId(companyId);
            existcompany.setCompanyName(company.getCompanyName());
            existcompany.setIndustryTypeId(company.getIndustryTypeId());

            Company updatedCompany = companyService.updateCompany(existcompany);
            return new ResponseEntity<Company> (updatedCompany,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Company> (HttpStatus.CONFLICT);
        }
    }
    @DeleteMapping(value="/deleteCompany/{companyId}")
    public String deleteCompany(@PathVariable("companyId") int companyId) {
        String cname = companyService.findByCompanyId(companyId).getCompanyName();
        companyService.deleteCompany(companyId);
        return ("Deleted product "+cname);
}
}