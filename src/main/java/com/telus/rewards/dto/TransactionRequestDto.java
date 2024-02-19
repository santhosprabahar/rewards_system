package com.telus.rewards.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionRequestDto {
    private String customerId;
    private Long amount;
    private LocalDate date;
}
