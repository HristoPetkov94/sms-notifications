package com.example.smsnotifications.service;

import com.example.smsnotifications.interfaces.TwilioSmsSenderInterface;
import com.example.smsnotifications.configuration.TwilioConfiguration;
import com.example.smsnotifications.model.SmsDetailsModel;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TwilioSmsSenderService implements TwilioSmsSenderInterface {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final TwilioConfiguration twilioConfiguration;

    @Autowired
    public TwilioSmsSenderService(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    private boolean isPhoneValid(String phoneNumber) {

        String regex = "^\\+?[0-9. ()-]{10,25}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }

    @Override
    public void sendSms(SmsDetailsModel smsDetails) {

        if (!isPhoneValid(smsDetails.getPhoneNumber())) {
            throw new IllegalArgumentException("Invalid phone number " + smsDetails.getPhoneNumber());
        }

        Twilio.init(twilioConfiguration.getAccountSid(), twilioConfiguration.getAuthToken());

        String number = smsDetails.getPhoneNumber().replaceAll("\\s+", "");
        PhoneNumber to = new PhoneNumber(number);
        PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
        String message = smsDetails.getMessage();

        MessageCreator creator = Message.creator(to, from, message);
        creator.create();

        logger.info("Send sms: " + smsDetails);
    }
}
