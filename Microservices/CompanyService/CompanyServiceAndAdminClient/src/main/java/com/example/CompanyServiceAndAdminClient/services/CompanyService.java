package com.example.CompanyServiceAndAdminClient.services;

import java.util.List;

import com.example.CompanyServiceAndAdminClient.entities.Company;

public interface CompanyService {

    List<Company> getAllCompany();
    Company findByCompanyId(int companyId);
    Company findProductByCompanyId(int companyId);
    List<Company> getIndustryTypeById(int industryTypeId);
    Company getCompanyByName(String companyName);
    Company addCompany(Company company);
    Company updateCompany(Company company);
    String deleteCompany(int companyId);
    List<Company> getAllCompaniesAllProducts(int industryTypeId);
}
