package com.olubunmee.USSDApplication.service.user;

import com.olubunmee.USSDApplication.data.dto.SmsRequest;
import com.olubunmee.USSDApplication.data.model.Destination;
import com.olubunmee.USSDApplication.data.model.Message;
import com.olubunmee.USSDApplication.data.model.User;
import com.olubunmee.USSDApplication.data.model.Wallet;
import com.olubunmee.USSDApplication.exception.UssdException;
import com.olubunmee.USSDApplication.repository.UserRepository;
import com.olubunmee.USSDApplication.service.sms.SmsService;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final SmsService smsService;

    @Override
    public void createAccount(String phone, String pin) {
        userRepository.save(User.builder()
                .phoneNumber(phone)
                .pin(pin)
                .wallet(Wallet.builder()
                        .accountNo(phone.substring(phone.length() - 10))
                        .build())
                .build());
    }

    @Override
    @Transactional
    public String deposit(BigDecimal amount, String phone) {
        User user = findUser(phone);
        user.getWallet().setBalance(user.getWallet().getBalance().add(amount));
        user.setHasPendingDeposit(false);
        userRepository.save(user);

        String message = "Deposit of" + amount + "Successful";
        smsService.send(SmsRequest.builder()
                .messages(List.of(Message.builder()
                        .destinations(List.of(Destination.builder()
                                .to(phone)
                                .build()))
                        .from("ussd app")
                        .text(message)
                        .build()))
                .build());
        return "Deposit of " + amount + " is Successful";
    }


    @Override
    @Transactional
    public String withdraw(BigDecimal amount, String phone) {
        User user = findUser(phone);

        if (checkBalance(phone).compareTo(amount) < 0) {
            user.setHasPendingWithdrawal(false);
            userRepository.save(user);
            return "Insufficient Balance";
        }

        user.getWallet().setBalance(user.getWallet().getBalance().subtract(amount));
        user.setHasPendingWithdrawal(false);
        userRepository.save(user);

        String message = "Withdrawal of" + amount + "Successful";
        smsService.send(SmsRequest.builder()
                .messages(List.of(Message.builder()
                        .destinations(List.of(Destination.builder()
                                .to(phone)
                                .build()))
                        .from("ussd app")
                        .text(message)
                        .build()))
                .build());
        return "Withdrawal of " + amount + " is Successful";
    }

    @Override
    public BigDecimal checkBalance(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber).orElseThrow(
                        () -> new UssdException("user not found")
                )
                .getWallet().getBalance();
    }

    @Override
    public boolean existByPhoneNumber(String phone) {
        return userRepository.existsByPhoneNumber(phone);
    }

    @Override
    public String getAccountNo(String phone) {
        return userRepository.findByPhoneNumber(phone).orElseThrow(
                () -> new UssdException("user not found")
        ).getWallet().getAccountNo();
    }

    @Override
    public boolean existAccountNo(String phone) {
        User user = userRepository.findByPhoneNumber(phone).orElse(null);
        if (user == null) return false;
        return user.getWallet().getAccountNo().matches(phone.substring(phone.length() - 10));
    }

    @Override
    public void setUpDeposit(String phone) {
        User user = findUser(phone);
        user.setHasPendingDeposit(true);
        userRepository.save(user);
    }

    private User findUser(String phone) {
        return userRepository.findByPhoneNumber(phone).orElseThrow(
                () -> new UssdException("user not found")
        );
    }

    @Override
    public void setUpWithdrawal(String phone) {
        User user = findUser(phone);
        user.setHasPendingWithdrawal(true);
        userRepository.save(user);
    }

    @Override
    public boolean hasPendingDeposit(String phone) {
        return findUser(phone).isHasPendingDeposit();
    }

    @PreDestroy
    public void cleanUp() {
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            user.setHasPendingWithdrawal(false);
            user.setHasPendingDeposit(false);
            userRepository.save(user);
        });
    }

    @Override
    public boolean hasPendingWithdrawal(String phone) {
        return findUser(phone).isHasPendingWithdrawal();
    }
}