package com.example.E_commerce.mapper;

import com.example.E_commerce.dto.OrderDTO;
import com.example.E_commerce.entity.Order;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderMapper {
    public static OrderDTO createOrderDTO(Order order){
        OrderDTO orderDTO= new OrderDTO();
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setQuantity(order.getOrderItem().getQuantity());
        orderDTO.setOrderStatus(order.getStatus().name());
        orderDTO.setBuyerId(order.getUser().getId());
        orderDTO.setProductid(order.getOrderItem().getId());
        orderDTO.setSellerId(order.getOrderItem().getProduct().getSeller().getId());
        orderDTO.setTotalAmount(order.getTotalAmount());
        orderDTO.setBuyerName(order.getUser().getName());
        orderDTO.setSellerName(order.getOrderItem().getProduct().getSeller().getName());
        orderDTO.setPricePerItem(order.getOrderItem().getProduct().getPrice());
        return orderDTO;
    }
}
