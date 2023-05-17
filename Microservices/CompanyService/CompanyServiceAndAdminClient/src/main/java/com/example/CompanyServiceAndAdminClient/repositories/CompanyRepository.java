package com.example.CompanyServiceAndAdminClient.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CompanyServiceAndAdminClient.entities.Company;

public interface CompanyRepository extends JpaRepository<Company,Integer>{
    Company findByCompanyId(int companyId);
    List<Company> findByIndustryTypeId(int industryTypeId);
    Company findBycompanyName(String name);
}
