package com.telus.rewards.service;

import com.telus.rewards.dto.*;
import com.telus.rewards.entity.Customer;
import com.telus.rewards.entity.Transaction;
import com.telus.rewards.exception.RewardsException;
import com.telus.rewards.repository.CustomerRepository;
import com.telus.rewards.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    
    @Autowired
    private CustomerRepository customerRepository;

    public RewardsResponse saveTransaction(TransactionRequestDto transactionRequestDto) throws RewardsException {
        Customer customer = customerRepository.findByCustomerId(transactionRequestDto.getCustomerId());
        long rewardPoints = this.getRewardPoints(transactionRequestDto, customer);

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionRequestDto.getAmount());
        transaction.setCustomer(customer);
        transaction.setRewardPoints(rewardPoints);
        transaction.setDate(transactionRequestDto.getDate());
        transactionRepository.save(transaction);
        return RewardsResponse.builder()
                .status("success")
                .data(TransactionResponseDto.builder()
                        .transactionId(transaction.getTransactionId())
                        .amount(transaction.getAmount())
                        .rewardPoints(transaction.getRewardPoints()).build())
                .build();
    }

    public List<RewardDetailsDto> getTransaction(String t1, String t2){
        LocalDate localDate1 = LocalDate.parse(t1);
        LocalDate localDate2 = LocalDate.parse(t2);
        List<RewardsReportDto> rewardsReportDtos =  transactionRepository.findByGroup(localDate1,localDate2);
        Map<String,List<RewardsGroupByDto>> map = rewardsReportDtos.stream().collect(Collectors.groupingBy(RewardsReportDto::getCustomerId,Collectors.mapping(e->new RewardsGroupByDto(e.getMonth(),e.getRewardPoints()),Collectors.toList())));
        List<RewardDetailsDto> rewardDetailsDtos = new ArrayList<>();
        for (String s : map.keySet()) {
            long sum = map.get(s).stream().mapToLong(RewardsGroupByDto::getPoints).sum();
            RewardsDto rewardsDto = new RewardsDto(sum,map.get(s));
            rewardDetailsDtos.add(new RewardDetailsDto(s,rewardsDto));
        }

        return rewardDetailsDtos;
    }

    private long getRewardPoints(TransactionRequestDto transactionRequestDto, Customer customer) throws RewardsException {
        if(customer == null) throw new RewardsException(RewardsError.CUSTOMER_DOES_NOT_EXIST);
        Long amount = transactionRequestDto.getAmount();
        long rewardPoints = 0L;

        if(amount > 100){
            long eligibleRewardPoints = ((amount - 100) * 2) + (50);
            rewardPoints = rewardPoints + eligibleRewardPoints;
        } else if(amount > 50){
            long eligibleRewardPoints = amount - 50;
            rewardPoints = rewardPoints + eligibleRewardPoints;
        }
        return rewardPoints;
    }
}
