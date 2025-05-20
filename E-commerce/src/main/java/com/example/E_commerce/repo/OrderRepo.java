package com.example.E_commerce.repo;

import com.example.E_commerce.entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends PagingAndSortingRepository<Order,Integer>, CrudRepository<Order,Integer> {

    @Query(value = "SELECT * FROM orders WHERE  user_id= :userId",nativeQuery = true)
    public List<Order> findByUserId(@Param("userId") int userId);
}
