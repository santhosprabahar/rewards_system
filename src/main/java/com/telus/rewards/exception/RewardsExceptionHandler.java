package com.telus.rewards.exception;

import com.telus.rewards.dto.RewardsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RewardsExceptionHandler {

    @ExceptionHandler(RewardsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public RewardsResponse handleRewardsException(RewardsException re, WebRequest webRequest){
        return RewardsResponse.builder()
                .status("failure")
                .errorCode(re.getError().getErrorCode())
                .errorDesc(re.getError().getErrorDesc())
                .build();
    }
}
