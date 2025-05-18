package com.example.E_commerce.service;


import com.example.E_commerce.dto.UserDTO;
import com.example.E_commerce.entity.User;
import com.example.E_commerce.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    public User save(User user){ return userRepo.save(user); }
    public Optional<User>  findBYId(int id) { return userRepo.findById(id); };
}
