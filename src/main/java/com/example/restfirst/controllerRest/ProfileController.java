package com.example.restfirst.controllerRest;

import com.example.restfirst.dto.EmailDto;
import com.example.restfirst.service.impl.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    private final EmailSenderService emailSenderService;

    @Autowired
    public ProfileController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @PostMapping(value="/email", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendMail(@RequestBody EmailDto emailDto) {
        try {
            emailSenderService.sendEmailForUniversity(
                    emailDto.getToEmail(),
                    emailDto.getBody(),
                    emailDto.getSubject());
            return new ResponseEntity<>(emailDto.getToEmail()+"SUCCESS", HttpStatus.OK);
        }
        catch (MailException mEx){
            mEx.printStackTrace();
            return new ResponseEntity<>(mEx.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}


