package com.sougata.domainApp.email.controller;

import com.sougata.domainApp.email.dto.EmailDto;
import com.sougata.domainApp.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService service;

    @PostMapping("/send")
    public ResponseEntity<EmailDto> sendMail(@RequestBody EmailDto emailDto) {
        try {
            service.sendMail(emailDto);
            return ResponseEntity.ok(emailDto);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
