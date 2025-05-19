package com.example.E_commerce.service;

import com.example.E_commerce.entity.Order;
import com.example.E_commerce.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;
    public Order save(Order order) {
       return orderRepo.save(order);
    }
}
