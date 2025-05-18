package com.example.E_commerce.service;

import com.example.E_commerce.entity.Address;
import com.example.E_commerce.repo.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepo addressRepo;
    public Optional<Address> findById(int id){ return addressRepo.findById(id); }
    public Address save(Address address){ return addressRepo.save(address); }
}
