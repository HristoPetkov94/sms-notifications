package com.example.smsnotifications.contoller;

import com.example.smsnotifications.interfaces.TwilioSmsSenderInterface;
import com.example.smsnotifications.model.SmsDetailsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/send-sms-message")
public class TwilioSmsSenderController {

    @Autowired
    private TwilioSmsSenderInterface twilioSmsSenderInterface;

    @PostMapping
    public void sendSms(@RequestBody SmsDetailsModel smsDetails, BindingResult bindingResult) throws ValidationException {

        if (bindingResult.hasErrors())
            throw new ValidationException("emailDetailsDTO is not valid");

        twilioSmsSenderInterface.sendSms(smsDetails);
    }
}
