package com.austin.companyms.companyservice.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.austin.companyms.companyservice.model.Company;
import com.austin.companyms.companyservice.service.CompanyService;

@RestController
@RequestMapping("/app/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping //view all companies
    public ResponseEntity<List<Company>> getAllCompanies(){
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @PostMapping //create a company
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        boolean test = companyService.createCompany(company);
       if (test){
         return new ResponseEntity<>("Company created Successfully", HttpStatus.CREATED);
       }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/{id}") //view one company
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){

        Company company = companyService.getCompanyById(id);
        if(company != null){
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}") // update
    public  ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company){
        if(companyService.updateCompany(id,company)){
            return new ResponseEntity<>("Company successfully updated", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable long id){
        if(companyService.deleteCompany(id)){
            return new ResponseEntity<>("Company deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
