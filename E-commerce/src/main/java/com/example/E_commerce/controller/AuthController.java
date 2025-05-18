package com.example.E_commerce.controller;

import com.example.E_commerce.Constant.USER_ROLE;
import com.example.E_commerce.dto.UserRequestDTO;
import com.example.E_commerce.entity.Address;
import com.example.E_commerce.entity.User;
import com.example.E_commerce.mapper.UserMapper;
import com.example.E_commerce.repo.UserRepo;
import com.example.E_commerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepo userRepo;
    //Register New User
    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@RequestBody UserRequestDTO userRequestDTO){
        User user= new User();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        try{
            USER_ROLE role= USER_ROLE.valueOf(userRequestDTO.getRole().toUpperCase());
            user.setRole(role);
        }catch (Exception ex){
          return ResponseEntity.badRequest().body("Invalid Role");
        }

        Address address= new Address();
        address.setCity(userRequestDTO.getCity());
        address.setState(userRequestDTO.getState());
        address.setStreet(userRequestDTO.getStreet());
        address.setZipCode(userRequestDTO.getZipcode());

        address.setUser(user);
        user.setAddress(address); //This is necessary for java object but not for database
        userRepo.save(user);
        return ResponseEntity.ok( UserMapper.createUserDTO(user));
    }
}
