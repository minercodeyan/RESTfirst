package com.example.restfirst.controllerRest;

import com.example.restfirst.dto.EmailDto;
import com.example.restfirst.dto.MessageDto;
import com.example.restfirst.model.User;
import com.example.restfirst.model.communicationentities.Massage;
import com.example.restfirst.repo.MessageRepo;
import com.example.restfirst.service.impl.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    private final EmailSenderService emailSenderService;
    private final MessageRepo messageRepo;

    @Autowired
    public ProfileController(EmailSenderService emailSenderService, MessageRepo messageRepo) {
        this.emailSenderService = emailSenderService;
        this.messageRepo = messageRepo;
    }

    @GetMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<Massage>> getAllMassages(){
        return new ResponseEntity<>(messageRepo.findAll(),HttpStatus.OK);
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

    @MessageMapping("/changeMessage")
    @SendTo("/topic/activity")
    public Massage change(MessageDto massageRequest){
        Massage massageResp = new Massage();
        massageResp.setDate(new Date());
        massageResp.setDescription(massageRequest.getDescription());
        massageResp.setUser(new User(6L));
        return messageRepo.save(massageResp);
    }




}


