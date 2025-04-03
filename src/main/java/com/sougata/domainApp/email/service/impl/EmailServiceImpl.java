package com.sougata.domainApp.email.service.impl;

import com.sougata.domainApp.email.dto.EmailDto;
import com.sougata.domainApp.email.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

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

    @Override
    public void sendMailHtml(EmailDto emailDto) {
        try {
            log.info("📧 Preparing email with html to: {}", emailDto.getMailTo());
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
            helper.setTo(emailDto.getMailTo());
            helper.setSubject(emailDto.getSubject());
            helper.setFrom("no.replyciapp23@gmail.com");
            String template = """
                    <html>
                        <div>
                        <h1>%s</h1>
                        </div>
                    </html>
                    """;
            String htmlContent = String.format(template, emailDto.getBody());
            helper.setText(htmlContent, true);
            javaMailSender.send(message);
        } catch (Exception e) {
            log.error("❌ Error sending email: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }


}
