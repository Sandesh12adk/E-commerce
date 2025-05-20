package com.example.E_commerce.repo;

import com.example.E_commerce.entity.CartItem;
import com.example.E_commerce.entity.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepo extends CrudRepository<CartItem,Integer> {

        @Transactional
        @Modifying
        @Query(value = "DELETE FROM cart WHERE `buyer(user)_id` = :buyerId AND product_id = :productId", nativeQuery = true)
        void deleteByUserIdAndProductId(@Param("buyerId") int buyerId, @Param("productId") int productId);
        public boolean existsByBuyerIdAndProductId(int buyerId, int productId);
        public List<CartItem> findByBuyerId(int buyerId);
        public Optional<CartItem> findByIdAndBuyerId(int cartId, int buyerId);



}
