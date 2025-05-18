package com.example.E_commerce.repo;

import com.example.E_commerce.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepo extends CrudRepository<Order,Integer> {
}
