package com.example.E_commerce.mapper;

import com.example.E_commerce.dto.UserDTO;
import com.example.E_commerce.entity.User;

public class UserMapper {
    public static UserDTO createUserDTO(User user){
        UserDTO userDTO= new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setId(user.getId());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setState(user.getAddress().getState());
        userDTO.setCity(user.getAddress().getCity());
        userDTO.setZipcode(user.getAddress().getZipCode());
        userDTO.setStreet(user.getAddress().getStreet());
        return userDTO;
    }
}
