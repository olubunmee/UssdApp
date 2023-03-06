package com.olubunmee.USSDApplication.service.sms;

import com.olubunmee.USSDApplication.data.dto.SmsRequest;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService{
    private final RestTemplate restTemplate;
    @Value("${SMS_API_KEY:}")
    private String smsApiKey;
    @Value("${ACCOUNT_SID:}")
    private String accountSid;
    @Value("${AUT_TOKEN}")
    private String authToken;

    @Override
    @Async
    public void send(SmsRequest request) {
        log.info("request => {}", request);
        log.info("key -> {}", smsApiKey);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", smsApiKey);
        HttpEntity<SmsRequest> req = new HttpEntity<>(request, httpHeaders);
        ResponseEntity<Object> response = restTemplate.postForEntity("https://nzlxjy.api.infobip.com/sms/2/text/advanced",
                req, Object.class);

        log.info("res => {}", response);
        log.info("status code => {}", response.getStatusCode());
    }

    @Override
    @Async
    public void sendTwilo(String fromPhoneNumber, String toPhoneNumber) {
        Twilio.init(accountSid, authToken);
        Message message = Message.creator(
                new PhoneNumber(toPhoneNumber),
                new PhoneNumber(fromPhoneNumber),
                "").create();
        log.info("Id -> {}",message.getAccountSid());
    }
}