package com.olubunmee.USSDApplication.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.List;
@Data
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class Wallet {
    @Id
    private String walletId;
    private String accountNo;
    private BigDecimal balance = BigDecimal.ZERO;
    private List <Transaction> transactionHistory;
}
