package com.example.IndustryType.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.IndustryType.models.IndustryType;

public interface IndustryTypeRepo extends JpaRepository<IndustryType,Integer>{
    IndustryType findByindustryTypeId(int industryTypeId);
    IndustryType findByindustryName(String industryName);
}
