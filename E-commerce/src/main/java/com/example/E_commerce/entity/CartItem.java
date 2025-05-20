package com.example.E_commerce.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "cart")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CartItem extends BaseEntity{
    @Column(nullable = false)
    private int quantity;
    //Cart-Product Relationship. A cartItem stores the id of a (single) product and same product can belong to different cart
    //by different users;
    @ManyToOne
    @JoinColumn(name = "ProductId")
    private Product product;
    //CartItem-Product RelationShip
    @ManyToOne
    @JoinColumn(name = "Buyer(User)_Id")
    private User buyer;
}
