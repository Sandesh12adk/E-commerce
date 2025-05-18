package com.example.E_commerce.mapper;

import com.example.E_commerce.dto.CartItemDTO;
import com.example.E_commerce.entity.CartItem;

public class CartItemMapper {
    public static CartItemDTO createCartItemDTO(CartItem cartItem){
        CartItemDTO cartItemDTO= new CartItemDTO();
        cartItemDTO.setBuyerId(cartItem.getBuyer().getId());
        cartItemDTO.setProductId(cartItem.getProduct().getId());
        cartItemDTO.setQuantity(cartItem.getQuantity());
        return cartItemDTO;
    }
}
