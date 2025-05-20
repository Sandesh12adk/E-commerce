package com.example.E_commerce.service.security.service;

import com.example.E_commerce.entity.User;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =userService.findUserByUserName(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return new UserPrincipal(user);
    }
}
