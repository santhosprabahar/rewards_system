package com.telus.rewards.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class RewardsDto {
    private Long totalPoints;
    private List<RewardsGroupByDto> groupByList;
}
