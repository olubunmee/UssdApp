package com.olubunmee.USSDApplication.data.model;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Document
@Data
@RequiredArgsConstructor
@Builder
public class User {
    @Id
    private String id;
    private String name;
    private String phoneNumber;
    private String pin;
    private Wallet wallet;
    private  boolean hasPendingDeposit;
    private  boolean hasPendingWithdrawal;

}
