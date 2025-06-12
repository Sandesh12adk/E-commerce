package com.example.account_service.controller;

import com.example.account_service.dto.CustomerRequest;
import com.example.account_service.dto.CustomerResponse;
import com.example.account_service.mapper.CustomerMapper;
import com.example.account_service.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @PostMapping("/register")
    public ResponseEntity<CustomerRequest> register( @Valid  @RequestBody CustomerRequest customerRequest) {
        customerService.save(CustomerMapper.toCustomer(customerRequest));
        return ResponseEntity.ok(customerRequest);
    }
}
