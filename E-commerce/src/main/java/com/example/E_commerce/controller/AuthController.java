
package com.example.E_commerce.controller;

import com.example.E_commerce.Constant.USER_ROLE;
import com.example.E_commerce.dto.LoginRequestDTO;
import com.example.E_commerce.dto.ProfileDetailDTO;
import com.example.E_commerce.dto.UserDTO;
import com.example.E_commerce.dto.UserRequestDTO;
import com.example.E_commerce.entity.Address;
import com.example.E_commerce.entity.User;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.mapper.UserMapper;
import com.example.E_commerce.repo.UserRepo;
import com.example.E_commerce.service.UserService;
import com.example.E_commerce.service.security.service.JWTservice;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name="Auth APIs")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JWTservice jwTservice;
    BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder(10);

    @PreAuthorize("permitAll()")
    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@RequestBody UserRequestDTO userRequestDTO){
        User user= new User();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userRequestDTO.getPassword()));
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

    @PreAuthorize("permitAll()")
    @PutMapping("updateprofile")
    public ResponseEntity<UserDTO> updateProfile(@Valid  @RequestBody UserRequestDTO userRequestDTO){
        int userId= JWTservice.getAuthenticatiedUser().getId();
        User user= userService.findBYId(userId).orElseThrow(()->
                new ResourceNotFoundException("Cannot Find the user with provided Id"));
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userRequestDTO.getPassword()));
        userService.save(user);
        return ResponseEntity.ok(UserMapper.createUserDTO(user));
    }
    @PreAuthorize("permitAll()")
    @PostMapping("/login")
    public ResponseEntity<String> returnToken(@RequestBody LoginRequestDTO loginRequestDTO){
        String jwtToken="";
        if(jwTservice.isAuthenticated(loginRequestDTO)){
            jwtToken= jwTservice.generateToken(loginRequestDTO.getUserName());
        }
        else{
            throw new AccessDeniedException("Sorry you are not the valid user");
        }
        return ResponseEntity.ok(jwtToken);
    }
    @GetMapping("my-profile")
    public ResponseEntity<ProfileDetailDTO> getProfileInfo(){
        User user= JWTservice.getAuthenticatiedUser();
        UserDTO userDTO= UserMapper.createUserDTO(user);
        ProfileDetailDTO profileDetailDTO= new ProfileDetailDTO();
        profileDetailDTO.setUserDTO(userDTO);
        profileDetailDTO.setRole(user.getRole().name());
        return ResponseEntity.ok(profileDetailDTO);
    }
}
