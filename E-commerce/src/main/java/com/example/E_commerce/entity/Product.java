package com.example.E_commerce.entity;


import com.example.E_commerce.Constant.PRODUCT_CATEGORY;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class Product extends BaseEntity{
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private int stockQuantity;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PRODUCT_CATEGORY category;
    @CreatedDate
    private LocalDate createdAt;
    //Product-Cart Relationship
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<CartItem> cartItemList;
    //Product-Seller RelationShip
    @ManyToOne
    @JoinColumn(name = "Seller_Id(UserId)")
    private User seller;
}

