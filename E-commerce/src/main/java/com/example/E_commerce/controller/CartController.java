package com.example.E_commerce.controller;

import com.example.E_commerce.dto.CartItemDTO;
import com.example.E_commerce.dto.CartItemRequestDTO;
import com.example.E_commerce.entity.CartItem;
import com.example.E_commerce.entity.Product;
import com.example.E_commerce.entity.User;
import com.example.E_commerce.exception.OutOfStock;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.mapper.CartItemMapper;
import com.example.E_commerce.service.CartItemService;
import com.example.E_commerce.service.ProductService;
import com.example.E_commerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartItemService cartItemService;
    @PostMapping("/add")
    public ResponseEntity<?> addToCart( @Valid @RequestBody CartItemRequestDTO cartItemRequestDTO){
        CartItem cartItem= new CartItem();
        User buyer= userService.findBYId(cartItemRequestDTO.getBuyerId()).orElseThrow(()->
                 new ResourceNotFoundException("Cannot Find the Buyer with provided Id"));
        Product product= productService.findById(cartItemRequestDTO.getProductId()).orElseThrow(()->
                new ResourceNotFoundException("Cannot Find the Product with provided Id"));
        if(cartItemRequestDTO.getQuantity()>product.getStockQuantity()){
           throw new OutOfStock("Sorry, We Don't have Suffieciend Producrs");
        }
        cartItem.setQuantity(cartItemRequestDTO.getQuantity());
        cartItem.setBuyer(buyer);
        cartItem.setProduct(product);
        cartItemService.save(cartItem);
        return ResponseEntity.ok(CartItemMapper.createCartItemDTO(cartItem));
    }
    @DeleteMapping("/delete/{itemId}")
    public ResponseEntity<String> deleteCartItem( @PathVariable int itemId){
        if(!cartItemService.existById(itemId)){
            throw new ResourceNotFoundException("No Item Found in cart with provided Id");
        }
        cartItemService.delete(itemId);
        return ResponseEntity.ok("Deleted");
    }
    @GetMapping("/getbyid/{cartItemId}")
    public ResponseEntity<CartItemDTO> findById(@PathVariable int cartItemId){
        CartItem cartItem= cartItemService.findById(cartItemId).orElseThrow(()->
                 new ResourceNotFoundException("Cannot Find the CartItem with provided id") );
        return ResponseEntity.ok(CartItemMapper.createCartItemDTO(cartItem));
    }
}
