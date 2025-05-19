package com.example.E_commerce.controller;

import com.example.E_commerce.dto.OrderRequestDTO;
import com.example.E_commerce.entity.Order;
import com.example.E_commerce.entity.OrderItem;
import com.example.E_commerce.entity.Product;
import com.example.E_commerce.entity.User;
import com.example.E_commerce.exception.OutOfStock;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.mapper.OrderMapper;
import com.example.E_commerce.service.OrderService;
import com.example.E_commerce.service.ProductService;
import com.example.E_commerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @PostMapping("/placeorder/{userId}")
    public ResponseEntity<?> placeOrder(@Valid  @RequestBody OrderRequestDTO orderRequestDTO, @PathVariable int userId){

        User buyer= userService.findBYId(userId).orElseThrow(()->
                new ResourceNotFoundException("Cannot Find the Buyer with provided Id"));
       Product product= productService.findById(orderRequestDTO.getProductId()).orElseThrow(()->
                new ResourceNotFoundException("Cannot Find the product with provided Id"));
       if(orderRequestDTO.getQuantity()> product.getStockQuantity()){
           throw  new OutOfStock("Out Of Stock");
       }
        Order order= new Order();
        order.setUser(buyer);
        OrderItem orderItem= new OrderItem();
        orderItem.setQuantity(orderRequestDTO.getQuantity());
        orderItem.setProduct(product);
        orderItem.setOrder(order);
        order.setOrderItem(orderItem);
        BigDecimal totalAmount= product.getPrice().multiply(BigDecimal.valueOf(orderRequestDTO.getQuantity()));
        order.setTotalAmount(totalAmount);
        orderService.save(order);
        return ResponseEntity.ok(OrderMapper.createOrderDTO(order));
    }
}
