package com.example.account_service.mapper;

import com.example.account_service.dto.CustomerRequest;
import com.example.account_service.dto.CustomerResponse;
import com.example.account_service.entity.Account;
import com.example.account_service.entity.Address;
import com.example.account_service.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerMapper {

    public static Customer toCustomer(CustomerRequest customerRequest){
        Customer customer= new Customer();
        customer.setName(customerRequest.getName());
        customer.setEmail(customerRequest.getEmail());
        customer.setOccupation(customerRequest.getOccupation());
        customer.setMobile_NO(customerRequest.getMobile_NO());
        customer.setDate_Of_Birth(customerRequest.getDate_Of_Birth());
        customer.setNational_Id_Number(customerRequest.getNational_Id_Number());

        Address address= new Address();
        address.setCity(customerRequest.getCity());
        address.setProvince(customerRequest.getProvince());
        address.setStreet(customerRequest.getStreet());
        address.setZipCode(customerRequest.getZipCode());
        address.setCustomer(customer);

        Account account= new Account();
        account.setAccountType(customerRequest.getAccountType());
        account.setBranch_Code(customerRequest.getBranch_Code());
        account.setAccountType(customerRequest.getAccountType());
        account.setInitial_Deposite_Amount(customerRequest.getInitial_Deposite_Amount());
        account.setCustomer(customer);

        customer.setAddress(address);
        customer.setAccount(account);
        return  customer;
    }
    public static CustomerResponse toCustomerResponse(CustomerRequest customerRequest){
       return (CustomerResponse) customerRequest;

    }
}
