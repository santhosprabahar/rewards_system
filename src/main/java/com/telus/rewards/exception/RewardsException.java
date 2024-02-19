package com.telus.rewards.exception;

import com.telus.rewards.dto.RewardsError;
import lombok.Getter;

@Getter
public class RewardsException extends Exception{

    private final RewardsError error;

    public RewardsException(RewardsError error){
        super(error.getErrorDesc());
        this.error = error;
    }
}
