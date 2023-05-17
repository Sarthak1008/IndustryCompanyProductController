package com.example.CompanyServiceAndAdminClient.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Table(name="company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {
    
    @Id
    @Column(name = "companyId")
    private int companyId;
    
    @Column(name="companyName")
    private String companyName;

    @Column(name="industryTypeId")
    private int industryTypeId;

    @Transient
    private List<Product> products = new ArrayList<>();
}
