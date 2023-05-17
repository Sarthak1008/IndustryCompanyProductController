package com.example.CompanyServiceAndAdminClient.services.Impl;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.CompanyServiceAndAdminClient.entities.Company;
import com.example.CompanyServiceAndAdminClient.entities.Product;
import com.example.CompanyServiceAndAdminClient.repositories.CompanyRepository;
import com.example.CompanyServiceAndAdminClient.services.CompanyService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Service
public class CompanyServiceImpl implements CompanyService{

    private Logger logger = LoggerFactory.getLogger(CompanyService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    @Override
    public Company findByCompanyId(int companyId) {
        return companyRepository.findByCompanyId(companyId);
    }

    @Override
    public List<Company> getIndustryTypeById(int industryTypeId) {
        return companyRepository.findByIndustryTypeId(industryTypeId);
    }

    @Override
    public Company getCompanyByName(String companyName) {
        return companyRepository.findBycompanyName(companyName);
    }

    @Override
    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company updateCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public String deleteCompany(int companyId) {
        Company company = companyRepository.getReferenceById(companyId);
        String name = "Deleted product "+ company.getCompanyName();
        companyRepository.delete(company);
        return name;
    }
    

    @Override
    public Company findProductByCompanyId(int companyId) {
        Company company = companyRepository.findByCompanyId(companyId);
        ArrayList<Product> forObject = restTemplate.getForObject("http://PRODUCT-SERVICE/product/getProductByCompanyId/"+company.getCompanyId(),ArrayList.class);
        logger.info("{} ",forObject);
        company.setProducts(forObject);
        return company;
    }

    @Override
    public List<Company> getAllCompaniesAllProducts(int industryTypeId) {
        List<Company> companies = companyRepository.findAll();
        System.out.println(companies.size());
        List<Company> newList = new ArrayList<>();
        for(Company c:companies){
            if(c.getIndustryTypeId()==industryTypeId){
            ArrayList<Product> forObject = restTemplate.getForObject("http://PRODUCT-SERVICE/product/getProductByCompanyId/"+c.getCompanyId(),ArrayList.class);
            logger.info("{} ",forObject);
            c.setProducts(forObject);
            newList.add(c);
            }
            
        }
        return newList;
    }

    
    
}
