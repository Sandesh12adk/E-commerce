package com.example.E_commerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AddressDTO {
    private int id;
    @NotBlank(message = "Street is required")
    protected String street;

    @NotBlank(message = "City is required")
    protected String city;

    @NotBlank(message = "State is required")
    protected String state;

    @NotBlank(message = "Zip code is required")
    @Pattern(regexp = "\\d{5}", message = "Zip code must be 5 digits")
    protected String zipCode;

}
