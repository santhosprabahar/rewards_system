package com.telus.rewards.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RewardsError {
    DUPLICATE_CUSTOMER_ID("ERR001","Duplicate customer id"),
    CUSTOMER_DOES_NOT_EXIST("ERR002","Customer doesn't exist");

    private String errorCode;
    private String errorDesc;
}
