package com.example.E_commerce.repo;

import com.example.E_commerce.entity.OrderItem;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderItemRepo extends PagingAndSortingRepository<OrderItem,Integer>  {
}
