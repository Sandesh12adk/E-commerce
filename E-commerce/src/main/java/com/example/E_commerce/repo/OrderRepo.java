package com.example.E_commerce.repo;

import com.example.E_commerce.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends PagingAndSortingRepository<Order,Integer>, CrudRepository<Order,Integer> {
}
