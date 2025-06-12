package com.example.account_service.entity;

import com.example.account_service.constant.ACCOUNT_TYPE;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class Account extends BaseEntity{
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ACCOUNT_TYPE accountType;
    @Column(nullable = false)
    private String initial_Deposite_Amount;
    @Column(nullable = false)
    private String branch_Code;
    @Column(nullable = false)
    @CreatedDate
    private LocalDate account_Opening_Date;

    //Account-Cusotmer Relation
    @JoinColumn(name = "Cusotmer_Id")
    @OneToOne
    private Customer customer;

}
