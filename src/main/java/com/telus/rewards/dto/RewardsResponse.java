package com.telus.rewards.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RewardsResponse {
    private String status;
    private Object data;
    private String errorCode;
    private String errorDesc;
}
