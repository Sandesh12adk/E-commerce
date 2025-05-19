package com.example.E_commerce.entity;

import com.example.E_commerce.Constant.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class Order extends BaseEntity{
    @CreatedDate
    private LocalDate orderDate;
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status=OrderStatus.PENDING;
    @Column(nullable = false)
    private BigDecimal totalAmount;
   //Order-User RelationShip
    @ManyToOne
    @JoinColumn(name = "UserId")
    private User user;
  //Order-Orderitem Relatiohship
    @OneToOne(mappedBy = "order",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private OrderItem orderItem;
}
