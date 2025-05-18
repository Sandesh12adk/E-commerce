package com.example.E_commerce.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductDTO extends ProductRequestDTO{
    private int id;
    private LocalDate createdAt;
    private int stockQuantity;
    private int sellerId;
}
