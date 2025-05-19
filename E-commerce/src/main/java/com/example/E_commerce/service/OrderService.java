package com.example.E_commerce.service;

import com.example.E_commerce.Constant.OrderStatus;
import com.example.E_commerce.entity.Order;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.repo.OrderRepo;
import org.antlr.v4.runtime.atn.OrderedATNConfigSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;
    public Order save(Order order) {return orderRepo.save(order);}
    public Optional<Order> finById(int id){ return orderRepo.findById(id); }
    public List<Order> findByUserId(int userId){ return orderRepo.findByUserId(userId); }
    public void updateStatus(String status, Order order){
        try {
            OrderStatus validStatus = OrderStatus.valueOf(status.toUpperCase());
            order.setStatus(validStatus);
        }catch (RuntimeException ex){
            throw new RuntimeException("Invalid Order Status");
        }
        orderRepo.save(order);
    }
}
