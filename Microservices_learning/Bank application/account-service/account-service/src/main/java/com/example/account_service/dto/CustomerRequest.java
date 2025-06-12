package com.example.account_service.dto;

import com.example.account_service.constant.ACCOUNT_TYPE;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Mobile number must be between 10 and 15 digits")
    private String mobile_NO;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate date_Of_Birth;

    @NotBlank(message = "National ID number is required")
    private String national_Id_Number;

    @NotBlank(message = "Occupation is required")
    private String occupation;

    @NotBlank(message = "Province is required")
    private String province;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "Zip code is required")
    @Pattern(regexp = "^[0-9]{4,10}$", message = "Zip code must be 4 to 10 digits")
    private String zipCode;

    @NotNull(message = "Account type is required")
    private ACCOUNT_TYPE accountType;

    @NotBlank(message = "Initial deposit amount is required")
    @Pattern(regexp = "\\d+(\\.\\d{1,2})?", message = "Initial deposit must be a valid amount")
    private String initial_Deposite_Amount;

    @NotBlank(message = "Branch code is required")
    private String branch_Code;
}
