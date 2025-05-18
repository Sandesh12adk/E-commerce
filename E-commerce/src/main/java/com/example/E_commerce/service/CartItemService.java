package com.example.E_commerce.service;

import com.example.E_commerce.entity.CartItem;
import com.example.E_commerce.repo.CartItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepo cartItemRepo;
    public CartItem save(CartItem cartItem){return cartItemRepo.save(cartItem);}
    public void delete(int itemId){ cartItemRepo.deleteById(itemId); }
    public  boolean existById(int itemId){ return cartItemRepo.existsById(itemId); }
}
