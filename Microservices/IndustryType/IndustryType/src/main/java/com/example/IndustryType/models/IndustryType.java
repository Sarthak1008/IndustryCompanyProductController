package com.example.IndustryType.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Table(name="industryType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IndustryType {
    
    @Id
    @Column(name = "industryTypeId")
    private int industryTypeId;
    
    @Column(name="industryName")
    private String industryName;

    @Transient
    private List<Company> companys = new ArrayList<>();
}
