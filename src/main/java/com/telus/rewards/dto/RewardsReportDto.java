package com.telus.rewards.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class RewardsReportDto {
    private String customerId;
    private int month;
    private Long rewardPoints;

    public RewardsReportDto(String customerId, int month, Long rewardPoints) {
        this.customerId = customerId;
        this.month = month;
        this.rewardPoints = rewardPoints;
    }
}
