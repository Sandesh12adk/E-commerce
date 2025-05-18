package com.example.E_commerce.service;

import com.example.E_commerce.entity.Product;
import com.example.E_commerce.repo.ProductrRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductrRepo productrRepo;
    public Product save(Product product){ return productrRepo.save(product); }
    public Optional<Product> findById(int id){ return productrRepo.findById(id); }
    public void delete(int id){ productrRepo.deleteById(id); }

}
