package com.olubunmee.USSDApplication.data.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Transaction {
    @Id
    private String transactionId;
    private final LocalDateTime localDateTime = LocalDateTime.now();
}
