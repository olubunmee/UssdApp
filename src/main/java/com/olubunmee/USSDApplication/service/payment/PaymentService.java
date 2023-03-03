package com.olubunmee.USSDApplication.service.payment;//package com.olubunmee.USSDApplication.service.payment;
//
//import com.flutterwave.rave.java.entry.virtualCards;

import com.flutterwave.rave.java.entry.virtualCards;
import com.flutterwave.rave.java.payload.fundcardpayload;
import com.flutterwave.rave.java.payload.withdrawcardpayload;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {

    @Async
    public void makePayment(String phone, BigDecimal amount) {
        virtualCards virtualCards = new virtualCards();
        fundcardpayload fundcardpayload = new fundcardpayload();
        fundcardpayload.setId(phone);
        fundcardpayload.setAmount(String.valueOf(amount));
        fundcardpayload.setDebit_currency("Naira");
        fundcardpayload.setSecret_key("Secret key");
        virtualCards.dovirtualcardfund(fundcardpayload);
    }

    @Async
    public void withdraw(String phone, BigDecimal amount){
        virtualCards virtualCards = new virtualCards();
        withdrawcardpayload withdrawcardpayload = new withdrawcardpayload();
        withdrawcardpayload.setCard_id(phone);
        withdrawcardpayload.setAmount(String.valueOf(amount));
        withdrawcardpayload.setSecret_key("Secret key");
        virtualCards.dovirtualcardfwithdraw(withdrawcardpayload);
    }

}
