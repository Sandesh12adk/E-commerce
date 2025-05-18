package com.example.E_commerce.entity;

import com.example.E_commerce.Constant.USER_ROLE;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity{
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @CreatedDate
    private LocalDate createdAt;
    @Enumerated(value =EnumType.STRING)
    private USER_ROLE role;
    //User-Address RelationShip
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Address address;
    //User-Order RelationShip
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Order> orderList;
   //User-CartItem RelationShip
    @OneToMany(mappedBy = "buyer",cascade = CascadeType.ALL)
    private List<CartItem> cartItemList;
    //User(seller)-Product RelationShip
   @OneToMany(mappedBy = "seller",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Product> productList;

}
