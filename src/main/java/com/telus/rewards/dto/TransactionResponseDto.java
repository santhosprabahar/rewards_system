package com.telus.rewards.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionResponseDto {
    private String transactionId;
    private Long amount;
    private Long rewardPoints;
}
