package com.example.E_commerce.dto;

import com.example.E_commerce.Constant.USER_ROLE;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {
    private int id;
    private String name;
    private String email;
    private USER_ROLE role= USER_ROLE.CUSTOMER;
    private String state;
    private String city;
    private String zipcode;
    private String street;
    private LocalDate createdAt;
}
