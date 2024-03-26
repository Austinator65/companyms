package com.austin.companyms.companyservice.service;

import java.util.List;

import com.austin.companyms.companyservice.model.Company;

public interface CompanyService{
    List<Company> getAllCompanies();
    boolean createCompany(Company company);

    Company getCompanyById(Long id);

    boolean deleteCompany(Long id);

    boolean updateCompany(Long id, Company company);
}
