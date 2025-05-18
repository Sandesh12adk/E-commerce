package com.example.E_commerce.repo;

import com.example.E_commerce.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductrRepo extends PagingAndSortingRepository<Product,Integer> {
}
