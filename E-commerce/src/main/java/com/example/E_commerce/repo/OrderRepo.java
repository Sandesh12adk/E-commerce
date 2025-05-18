package com.example.E_commerce.repo;

import com.example.E_commerce.entity.Order;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderRepo extends PagingAndSortingRepository<Order,Integer> {
}
