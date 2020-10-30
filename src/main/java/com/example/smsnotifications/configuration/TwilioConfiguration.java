package com.example.smsnotifications.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class TwilioConfiguration {

    @Value("${sms.twilio.account_sid}")
    private String accountSid;

    @Value("${sms.twilio.auth_token}")
    private String authToken;

    @Value("${sms.twilio.trial_number}")
    private String trialNumber;
}
