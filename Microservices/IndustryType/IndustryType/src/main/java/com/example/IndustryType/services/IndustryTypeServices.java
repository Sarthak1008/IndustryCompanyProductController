package com.example.IndustryType.services;

import java.util.List;

import com.example.IndustryType.models.IndustryType;

public interface IndustryTypeServices {
    List<IndustryType> getAllIndustryType();
    IndustryType findByIndustryTypeId(int industryTypeId);
    IndustryType getIndustryTypeByName(String industryType);
    IndustryType addIndustryType(IndustryType industryType);
    IndustryType updateIndustryType(IndustryType industryType);
    String deleteIndustryType(int industryType);
}
