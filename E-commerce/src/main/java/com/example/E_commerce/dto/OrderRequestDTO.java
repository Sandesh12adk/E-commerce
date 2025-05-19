package com.example.E_commerce.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class OrderRequestDTO {
    @Min(value = 1, message = "Pleae provide valid product Id which is non negative integer")
    private int productId;
    @Min(value = 1,message = "Item Quantity should be atleast one")
    private int quantity;
}
