package com.telus.rewards.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RewardDetailsDto {

    private String customerId;
    private RewardsDto rewardsDetails;
}
