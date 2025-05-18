package com.example.E_commerce.mapper;

import com.example.E_commerce.dto.AddressDTO;
import com.example.E_commerce.entity.Address;

public class AddressMapper {
    public static AddressDTO createAddressDTO(Address address){
        AddressDTO addressDTO= new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setZipCode(address.getZipCode());
        addressDTO.setStreet(address.getStreet());
        return addressDTO;
    }
}
