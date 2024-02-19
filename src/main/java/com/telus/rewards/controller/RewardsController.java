package com.telus.rewards.controller;

import com.telus.rewards.dto.*;
import com.telus.rewards.exception.RewardsException;
import com.telus.rewards.service.CustomerService;
import com.telus.rewards.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rewards")
public class RewardsController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping(value = "/customer/save",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public RewardsResponse saveCustomer(@RequestBody CustomerDto customerDto) throws RewardsException {
        customerService.saveCustomer(customerDto);
        return RewardsResponse.builder().status("success").build();
    }

    @PostMapping(value = "/transaction/save",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public RewardsResponse saveTransaction(@RequestBody TransactionRequestDto transactionRequestDto) throws  RewardsException{
        return transactionService.saveTransaction(transactionRequestDto);
    }

    @GetMapping(value = "/transaction",produces = MediaType.APPLICATION_JSON_VALUE)
    public RewardsResponse getTransaction(@RequestParam(name = "startDate") String startDate,@RequestParam(name = "endDate") String endDate) throws  RewardsException{
        List<RewardDetailsDto> list =  transactionService.getTransaction(startDate,endDate);
        return RewardsResponse.builder()
                .status("success")
                .data(list)
                .build();
    }
}
