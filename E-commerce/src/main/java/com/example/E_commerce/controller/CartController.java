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
import com.example.E_commerce.service.security.service.JWTservice;
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
        int buyerId= JWTservice.getAuthenticatiedUser().getId();
        User buyer= userService.findBYId(buyerId).orElseThrow(()->
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
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCartItem( @RequestParam int productId){
        int buyerId= JWTservice.getAuthenticatiedUser().getId();
        if(!cartItemService.existsByBuyerIdAndProductId(buyerId,productId)){
            throw new ResourceNotFoundException("No Item Found in cart with provided Id");
        }
        cartItemService.deleteByUserIdAndProductId(buyerId,productId);
        return ResponseEntity.ok("Deleted");
    }
    @GetMapping("/findbyid/{cartItemId}")
    public ResponseEntity<CartItemDTO> findById(@PathVariable int cartItemId){
        CartItem cartItem= cartItemService.findById(cartItemId).orElseThrow(()->
                 new ResourceNotFoundException("Cannot Find the CartItem with provided Id") );
        return ResponseEntity.ok(CartItemMapper.createCartItemDTO(cartItem));
    }
    @PutMapping("updatequantity/{cartItemId}/{quantity}")
    public ResponseEntity<String> updateTheQuantityOfCartItem(@PathVariable int cartItemId, @PathVariable int quantity){
          CartItem cartItem= cartItemService.findById(cartItemId).orElseThrow(()->
                new ResourceNotFoundException("Cannot Find the CartItem with provided Id"));
          cartItem.setQuantity(quantity);
          cartItemService.save(cartItem);
          return ResponseEntity.ok("Updated Successfully");
    }
}
