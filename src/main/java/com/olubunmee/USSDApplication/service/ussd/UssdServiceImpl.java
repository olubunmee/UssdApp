package com.olubunmee.USSDApplication.service.ussd;

import com.olubunmee.USSDApplication.data.dto.UssdRequest;
import com.olubunmee.USSDApplication.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UssdServiceImpl implements UssdService {
    private final UserService userService;

    @Override
    public String performUssdOperation(UssdRequest request) {
        switch (request.getAction()) {
            case BALANCE -> {
                return getBalanceMenu(request.getPhone());
            }
            case DEPOSIT -> {
                userService.setUpDeposit(request.getPhone());
                return """
                        Enter Amount to Deposit
                        1. 1,000
                        2. 5,000
                        3. 10,000
                        """;
            }
            case WITHDRAW -> {
                userService.setUpWithdrawal(request.getPhone());
                return """
                        Enter Amount to Withdraw
                        1. 1,000
                        2. 5,000
                        3. 10,000
                        """;
            }
            case CREATE_ACCOUNT -> {
                return userService.existAccountNo(request.getPhone()) ? "Account already Exist" : createAccount(request);
            }
            case LOAD_MENU -> {
                return """
                        Welcome to OLUBUNMEE USSD APP,
                        what would you like to do?
                        1. Create Account
                        2. Deposit
                        3. Withdraw
                        4. Balance
                        """;
            }
        }
        return null;
    }


    private String createAccount(UssdRequest request) {
        userService.createAccount(request.getPhone(), request.getPin());
        return "Account Created Successfully with account no " + userService.getAccountNo(request.getPhone());
    }

    private String getBalanceMenu(String phone) {
        return "Your Balance is " + userService.checkBalance(phone);
    }
}
