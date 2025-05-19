package com.example.E_commerce.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OrderDTO {
    private LocalDate orderDate;
    private String orderStatus;
    private String buyerName;
    private int buyerId;
    private BigDecimal totalAmount;
    private int quantity;
    private int productid;
    private int sellerId;
    private String sellerName;
    private BigDecimal pricePerItem;
}
