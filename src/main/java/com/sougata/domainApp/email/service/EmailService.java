package com.sougata.domainApp.email.service;

import com.sougata.domainApp.email.dto.EmailDto;

public interface EmailService {
    void sendMail(EmailDto emailDto);
}
