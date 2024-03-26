package com.austin.companyms.companyservice.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.austin.companyms.companyservice.model.Company;
import com.austin.companyms.companyservice.repository.CompanyRepository;
import com.austin.companyms.companyservice.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean createCompany(Company company) {

        boolean test = companyRepository.findByName(company.getName()).isPresent();

        if(!test){
            companyRepository.save(company);
            return true;
        }
        return false;
    }

    @Override
    public Company getCompanyById(Long id) {
        Company company = companyRepository.findById(id).orElse(null);
       // System.out.println(company.getJobs());
        return company;
    }


    @Override
    public boolean updateCompany(Long id, Company updateCompany) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if(optionalCompany.isPresent()){
            Company company = optionalCompany.get();
            company.setName(updateCompany.getName());
            company.setDescription(updateCompany.getDescription());
            return true;
        }
        return false;
    }
    @Override
    public boolean deleteCompany(Long id) {
        if(companyRepository.findById(id).isPresent()){
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
