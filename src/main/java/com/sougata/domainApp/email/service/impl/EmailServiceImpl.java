package com.sougata.domainApp.email.service.impl;

import com.sougata.domainApp.email.dto.EmailDto;
import com.sougata.domainApp.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;

    @Override
    public void sendMail(EmailDto dto) {
        try {
            log.info("📧 Preparing email to: {}", dto.getMailTo());

            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(dto.getMailTo());
            mail.setSubject(dto.getSubject());
            mail.setText(dto.getBody());
            javaMailSender.send(mail);
            log.info("✅ Email sent successfully to: {}", dto.getMailTo());
        } catch (Exception e) {
            log.error("❌ Error sending email: {}", e.getMessage(), e);
            throw new RuntimeException("Error sending email: " + e.getMessage());
        }
    }
}
