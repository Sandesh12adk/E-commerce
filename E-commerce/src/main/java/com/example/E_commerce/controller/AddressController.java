package com.example.E_commerce.controller;

import com.example.E_commerce.dto.AddressDTO;
import com.example.E_commerce.dto.AddressRequestDTO;
import com.example.E_commerce.entity.Address;
import com.example.E_commerce.entity.User;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.mapper.AddressMapper;
import com.example.E_commerce.service.AddressService;
import com.example.E_commerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    AddressService addressService;
    @Autowired
    UserService userService;
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAddress(@Valid  @RequestBody AddressRequestDTO addressRequestDTO, @PathVariable int id){

        int loggedInUserId=id;
        Address address= addressService.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Cannot find the address with Provided Id:"+ id));
        address.setCity(addressRequestDTO.getCity());
        address.setState(addressRequestDTO.getState());
        address.setStreet(addressRequestDTO.getStreet());
        address.setZipCode(addressRequestDTO.getZipCode());
        addressService.save(address);
        return ResponseEntity.ok(AddressMapper.createAddressDTO(address));
    }
    @GetMapping("/get-address-by-userid/{userId}")
    public ResponseEntity<AddressDTO> getUserAddress(@PathVariable int userId){
        User user= userService.findBYId(userId).orElseThrow(()->
                new ResourceNotFoundException("Cannot find the user with provided Id"));
        Address address= user.getAddress();
        return ResponseEntity.ok(AddressMapper.createAddressDTO(address));
    }
}
