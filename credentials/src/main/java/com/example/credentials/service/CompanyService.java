package com.example.credentials.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.credentials.model.Company;
import com.example.credentials.repository.CompanyRepository;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(int id) {
        return companyRepository.findById(id).orElse(null);
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompany(int id, Company company) {
        if (companyRepository.existsById(id)) {
            company.setId(id);
            return companyRepository.save(company);
        }
        return null;
    }

    public void deleteCompany(int id) {
        companyRepository.deleteById(id);
    }

    // Generate unique 4-digit ID
    public int generateUniqueId() {
        int uniqueId;
        do {
            uniqueId = 1000 + (int) (Math.random() * 9000); // Generates a number between 1000 and 9999
        } while (companyRepository.existsById(uniqueId)); // Check if ID already exists
        return uniqueId;
    }
}
