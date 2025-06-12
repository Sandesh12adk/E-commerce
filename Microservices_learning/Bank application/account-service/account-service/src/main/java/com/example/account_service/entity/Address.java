package com.example.account_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)  // This will create the hash code of the super class also
public class Address extends BaseEntity{
    @Column(nullable = false)
    private String province;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String zipCode;

    //Address-CustomerInfo Relation
    @JoinColumn(name = "customer_id")
    @OneToOne
    private Customer customer;
}
