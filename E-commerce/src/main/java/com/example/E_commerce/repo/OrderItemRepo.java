package com.example.E_commerce.repo;

import com.example.E_commerce.entity.OrderItem;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepo extends PagingAndSortingRepository<OrderItem,Integer>  {
}
