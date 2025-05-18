package com.example.E_commerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CartItemRequestDTO {

    @Min(value = 1, message = "Item quantity should be at least one")
    private int quantity;

    @Min(value = 1, message = "Buyer ID must be a positive number")
    private int buyerId;

    @Min(value = 1, message = "Product ID must be a positive number")
    private int productId;
}
