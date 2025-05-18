package com.example.E_commerce.dto;


import lombok.Data;


@Data
public class UserRequestDTO {
    private String name;
    private String password;
    private String email;
    private String role;
    private String state;
    private String city;
    private String zipcode;
    private String street;

}
