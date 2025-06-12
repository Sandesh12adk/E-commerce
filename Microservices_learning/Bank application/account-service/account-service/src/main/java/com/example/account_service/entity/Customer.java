package com.example.account_service.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Customer extends BaseEntity{
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String mobile_NO;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private LocalDate date_Of_Birth;
    @Column(nullable = false)
    private String national_Id_Number;
    @Column(nullable = false)
    private String occupation;

    //Customer-Address Relation
    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    private Address address;

    //Customer-Account Relation
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Account account;

}
