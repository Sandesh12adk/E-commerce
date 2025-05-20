package com.example.E_commerce.controller;

import com.example.E_commerce.Constant.USER_ROLE;
import com.example.E_commerce.dto.OrderDTO;
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
import com.example.E_commerce.service.security.service.JWTservice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/placeorder")
    public ResponseEntity<?> placeOrder(@Valid  @RequestBody OrderRequestDTO orderRequestDTO){
       int userId= JWTservice.getAuthenticatiedUser().getId();
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
    //Later Make this to list Current Users Orders
    @PreAuthorize("hasRole('CUSTOMER','SELLER')")
    @GetMapping("/findall")
    public ResponseEntity<List<OrderDTO>> listOrders(){
        int currentuserid= JWTservice.getAuthenticatiedUser().getId();
        List<OrderDTO> orderDTOList= new ArrayList<>();
        orderService.findByUserId(currentuserid).forEach((order)->
                orderDTOList.add(OrderMapper.createOrderDTO(order))
        );
        return ResponseEntity.ok(orderDTOList);
    }

    @PreAuthorize("hasRole('SELLER')")
     @PutMapping("/update-order-status")
    public ResponseEntity<String> update(@RequestParam String status,@RequestParam int orderId){
        int sellerId= JWTservice.getAuthenticatiedUser().getId();
         Order order= orderService.finById(orderId).orElseThrow(()->
                 new ResourceNotFoundException("Cannot find the Order with Id:"+ orderId ));
         if(order.getOrderItem().getProduct().getSeller().getId()!=sellerId){
             throw new ResourceNotFoundException("Cannot find the Order with Id:"+ orderId );
         }
         orderService.updateStatus(status,order);
         return ResponseEntity.ok("Updated Successfully");
     }
}
