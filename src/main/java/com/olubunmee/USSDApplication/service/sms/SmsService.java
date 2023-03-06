package com.olubunmee.USSDApplication.service.sms;

import com.olubunmee.USSDApplication.data.dto.SmsRequest;
import org.springframework.stereotype.Service;

@Service
public interface SmsService {
    void send(SmsRequest request);
    void sendTwilo(String fromPhoneNumber, String toPhoneNumber);
}
