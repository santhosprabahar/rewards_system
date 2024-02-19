package com.telus.rewards.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@NoArgsConstructor
public class Transaction extends AbstractEntity{
    @UuidGenerator
    private String transactionId;
    private Long amount;
    private Long rewardPoints;
    @ManyToOne
    @JoinColumn(referencedColumnName = "pkey",name = "customer_fkey")
    private Customer customer;
    private LocalDate date = LocalDate.now();
}
