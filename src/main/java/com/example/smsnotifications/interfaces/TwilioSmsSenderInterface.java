package com.example.smsnotifications.interfaces;

import com.example.smsnotifications.model.SmsDetailsModel;

public interface TwilioSmsSenderInterface {
    void sendSms(SmsDetailsModel smsDetails);
}
