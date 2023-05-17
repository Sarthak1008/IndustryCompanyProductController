package com.example.IndustryType.controllers;

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

import com.example.IndustryType.models.IndustryType;
import com.example.IndustryType.services.IndustryTypeServices;

@RestController
@RequestMapping("/industry")
public class IndustryController {

    @Autowired
    private IndustryTypeServices industryTypeServices;

    @GetMapping(value="/getIndustries")
    public List<IndustryType> getAllIndustries() {
        return industryTypeServices.getAllIndustryType();
    }

    @GetMapping(value="/getIndustryById/{industryId}")
    public ResponseEntity<IndustryType> getIndustryById(@PathVariable("industryId") int industryId) {
        try {
            IndustryType industryType = industryTypeServices.findByIndustryTypeId(industryId);
            return new ResponseEntity<IndustryType> (industryType,HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<IndustryType> (HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/getIndustryByName/industryName")
    public ResponseEntity<IndustryType> getIndustryByName(@RequestParam("industryName") String industryName) {
        try {
            IndustryType industryType = industryTypeServices.getIndustryTypeByName(industryName);
            return new ResponseEntity<IndustryType> (industryType,HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<IndustryType> (HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping(value = "/addIndustry")
    public IndustryType addIndustry(@RequestBody IndustryType industryType){
        return industryTypeServices.addIndustryType(industryType);
    }

    @PutMapping(value="/updateCompany/{industryTypeId}")
    public ResponseEntity<IndustryType> updateProduct(@PathVariable(value = "industryTypeId") int industryTypeId,@RequestBody IndustryType industryType) {
        try {
            IndustryType existIndustry = industryTypeServices.findByIndustryTypeId(industryTypeId);
            existIndustry.setIndustryTypeId(industryTypeId);
            existIndustry.setIndustryName(industryType.getIndustryName());

            IndustryType updatedIndustry = industryTypeServices.updateIndustryType(existIndustry);
            return new ResponseEntity<IndustryType> (updatedIndustry,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<IndustryType> (HttpStatus.CONFLICT);
        }
    }
    
    @DeleteMapping(value="/deleteIndustry/{industryType}")
    public String deleteIndustry(@PathVariable("industryType") int industryType) {
        String iname = industryTypeServices.findByIndustryTypeId(industryType).getIndustryName();
        industryTypeServices.deleteIndustryType(industryType);
        return ("Deleted product "+iname);
}
}
