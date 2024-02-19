package com.telus.rewards.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@MappedSuperclass
@EntityListeners(value = {PersistenceListener.class})
@Getter
@Setter
public class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pkey")
    private Long pkey;
    private Timestamp created;
    private Timestamp changed;
}
