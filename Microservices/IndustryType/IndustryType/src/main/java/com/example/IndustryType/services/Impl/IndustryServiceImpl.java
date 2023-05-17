package com.example.IndustryType.services.Impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.IndustryType.models.Company;
import com.example.IndustryType.models.IndustryType;
import com.example.IndustryType.repositories.IndustryTypeRepo;
import com.example.IndustryType.services.IndustryTypeServices;

@Service
public class IndustryServiceImpl implements IndustryTypeServices{

    @Autowired
    private IndustryTypeRepo industryTypeRepo;

    private Logger logger = LoggerFactory.getLogger(IndustryTypeServices.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<IndustryType> getAllIndustryType() {
        return industryTypeRepo.findAll();
    }

    @Override
    public IndustryType findByIndustryTypeId(int industryTypeId) {
        IndustryType industryType = industryTypeRepo.findByindustryTypeId(industryTypeId);
        List<Company> forObject = restTemplate.getForObject("http://COMPANY-SERVICE/company/getAllCompaniesAllProductsOfIndustry/"+industryTypeId,ArrayList.class);
        logger.info("{} ",forObject);
        industryType.setCompanys(forObject);
        return industryType;
    }
    

    @Override
    public IndustryType getIndustryTypeByName(String industryType) {
        return industryTypeRepo.findByindustryName(industryType);
    }

    @Override
    public IndustryType addIndustryType(IndustryType industryType) {
        return industryTypeRepo.save(industryType);
    }

    @Override
    public IndustryType updateIndustryType(IndustryType industryType) {
        return industryTypeRepo.save(industryType);
    }

    @Override
    public String deleteIndustryType(int industryTypeId) {
        IndustryType industryType = industryTypeRepo.findByindustryTypeId(industryTypeId);
        String iname = "Deleted Industry" + industryType.getIndustryName();
        industryTypeRepo.delete(industryType);
        return iname;
    }
    
}
