package com.example.E_commerce.repo;

import com.example.E_commerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

public interface UserRepo extends JpaRepository<User, Integer> {
}
