package com.telus.rewards.service;

import com.telus.rewards.dto.CustomerDto;
import com.telus.rewards.dto.RewardsError;
import com.telus.rewards.dto.RewardsResponse;
import com.telus.rewards.entity.Customer;
import com.telus.rewards.exception.RewardsException;
import com.telus.rewards.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void saveCustomer(CustomerDto customerDto) throws RewardsException {
        Customer customer = new Customer();
        customer.setCustomerId(customerDto.getCustomerId());
        customer.setCustomerName(customerDto.getCustomerName());
        try {
            customerRepository.save(customer);
        }catch (DataIntegrityViolationException dataIntegrityViolationException){
            throw new RewardsException(RewardsError.DUPLICATE_CUSTOMER_ID);
        }
    }
}
