package com.olubunmee.USSDApplication.controller;

import com.olubunmee.USSDApplication.data.dto.UssdRequest;
import com.olubunmee.USSDApplication.data.model.Action;
import com.olubunmee.USSDApplication.service.user.UserService;
import com.olubunmee.USSDApplication.service.ussd.UssdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/ussd")
public class UssdController {
    private final UssdService ussdService;
    private final UserService userService;

    @PostMapping("/loadMenu")
    public String performUssdOperation(@RequestBody UssdRequest ussdRequest) {

        if (!userService.existByPhoneNumber(ussdRequest.getPhone()) && ussdRequest.getCommand().equals("1")) {
            userService.createAccount(ussdRequest.getPhone(), ussdRequest.getPin());
            return "An account had been created for you with " + ussdRequest.getPhone();
        }

        switch (ussdRequest.getCommand()) {
            case "1" -> {
                if (userService.hasPendingDeposit(ussdRequest.getPhone())) {
                    return userService.deposit(BigDecimal.valueOf(1000), ussdRequest.getPhone());
                }
                if (userService.hasPendingWithdrawal(ussdRequest.getPhone())) {
                    return userService.withdraw(BigDecimal.valueOf(1000), ussdRequest.getPhone());
                }
                ussdRequest.setAction(Action.CREATE_ACCOUNT);
            }
            case "2" -> {
                if (userService.hasPendingDeposit(ussdRequest.getPhone())) {
                    return userService.deposit(BigDecimal.valueOf(5000), ussdRequest.getPhone());
                }
                if (userService.hasPendingWithdrawal(ussdRequest.getPhone())) {
                    return userService.withdraw(BigDecimal.valueOf(5000), ussdRequest.getPhone());
                }
                ussdRequest.setAction(Action.DEPOSIT);
            }
            case "3" -> {
                if (userService.hasPendingDeposit(ussdRequest.getPhone())) {
                    return userService.deposit(BigDecimal.valueOf(10000), ussdRequest.getPhone());
                }
                if (userService.hasPendingWithdrawal(ussdRequest.getPhone())) {
                    return userService.withdraw(BigDecimal.valueOf(10000), ussdRequest.getPhone());
                }
                ussdRequest.setAction(Action.WITHDRAW);
            }
            case "4" -> ussdRequest.setAction(Action.BALANCE);
            case "*000#" -> ussdRequest.setAction(Action.LOAD_MENU);
        }
        return ussdService.performUssdOperation(ussdRequest);
    }

}
