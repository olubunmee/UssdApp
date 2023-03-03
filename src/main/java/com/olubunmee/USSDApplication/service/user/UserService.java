package com.olubunmee.USSDApplication.service.user;

import java.math.BigDecimal;

public interface UserService {
    void createAccount(String phone,String pin);
    String deposit(BigDecimal amount, String phone);
    String withdraw(BigDecimal amount, String phome);
    BigDecimal checkBalance(String phoneNumber);
    boolean existByPhoneNumber(String phone);
    String getAccountNo(String phone);
    boolean existAccountNo(String phone);
    void setUpDeposit(String phone);
    void setUpWithdrawal(String phone);
    boolean hasPendingDeposit(String phone);
    boolean hasPendingWithdrawal(String phone);
}
