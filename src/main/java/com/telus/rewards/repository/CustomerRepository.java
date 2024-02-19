package com.telus.rewards.repository;

import com.telus.rewards.entity.Customer;
import org.springframework.data.repository.CrudRepository;


public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByCustomerId(String customerId);
}
