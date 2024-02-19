package com.telus.rewards.entity;


import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.sql.Timestamp;
import java.util.Calendar;


public class PersistenceListener {

  	@PrePersist
    void onPreCreate(Object entity) {
        AbstractEntity baseEntity = (AbstractEntity) entity;
        baseEntity.setCreated(new Timestamp(Calendar.getInstance().getTime().getTime()));
        baseEntity.setChanged(new Timestamp(Calendar.getInstance().getTime().getTime()));
    }

    @PreUpdate
    void onPreUpdate(Object entity) {
        AbstractEntity baseEntity = (AbstractEntity) entity;
        baseEntity.setChanged(new Timestamp(Calendar.getInstance().getTime().getTime()));
    }
}

