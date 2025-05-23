package com.example.E_commerce.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Table(name = "orderitem")
@Data
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class OrderItem extends BaseEntity{
    private int quantity;
    @CreatedDate
    private LocalDate orderPlacedAt;
    //OrderItem-Order RelationShop
    @ManyToOne
    @JoinColumn(name = "Order_Id")
    private Order order;
    //OrderItem-Product RelationShip
    @OneToOne
    @JoinColumn(name = "Product_Id")
    private Product product;
}