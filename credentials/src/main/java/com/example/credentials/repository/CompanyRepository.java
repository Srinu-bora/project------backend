package com.example.credentials.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.credentials.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
