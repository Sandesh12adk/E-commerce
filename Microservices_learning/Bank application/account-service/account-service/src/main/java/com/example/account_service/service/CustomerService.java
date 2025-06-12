package com.example.account_service.service;

import com.example.account_service.dto.CustomerRequest;
import com.example.account_service.entity.Customer;
import com.example.account_service.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;
    public Customer save(Customer customer){ return customerRepo.save(customer); }
}
