package com.example.E_commerce.service;

import com.example.E_commerce.entity.CartItem;
import com.example.E_commerce.repo.CartItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepo cartItemRepo;
    public CartItem save(CartItem cartItem){return cartItemRepo.save(cartItem);}
    public void delete(int itemId){ cartItemRepo.deleteById(itemId); }
    public  boolean existById(int itemId){ return cartItemRepo.existsById(itemId); }
    public Optional<CartItem> findById(int id){ return cartItemRepo.findById(id); }
    public List<CartItem> findByBuyerId(int buyerId){ return cartItemRepo.findByBuyerId(buyerId); }

    public void deleteByUserIdAndProductId(int buyerId,int productId) {
         cartItemRepo.deleteByUserIdAndProductId(buyerId,productId); }

    public boolean existsByBuyerIdAndProductId(int buyerId, int productId){
       return cartItemRepo.existsByBuyerIdAndProductId(buyerId,productId);  }
}
