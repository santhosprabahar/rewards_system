package com.telus.rewards.repository;

import com.telus.rewards.dto.RewardsReportDto;
import com.telus.rewards.entity.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction,Long> {

    List<Transaction> findAllByCreatedBetween(Timestamp t1, Timestamp t2);

    @Query("select new com.telus.rewards.dto.RewardsReportDto(c.customerId,month(t.date),sum(t.rewardPoints)) from Transaction t inner join Customer c on" +
            "(c.pkey=t.customer.pkey) where t.date between :t1 and :t2 group by month(t.date),c.customerId")
    List<RewardsReportDto> findByGroup(LocalDate t1,LocalDate t2);
}
