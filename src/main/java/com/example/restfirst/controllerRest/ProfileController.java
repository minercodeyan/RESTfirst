package com.example.restfirst.controllerRest;

import com.example.restfirst.dto.EmailDto;
import com.example.restfirst.dto.MessageDto;
import com.example.restfirst.model.JSONViews.Views;
import com.example.restfirst.model.communicationEntities.Message;
import com.example.restfirst.service.MessageService;
import com.example.restfirst.service.impl.EmailSenderService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    private final EmailSenderService emailSenderService;
    private final MessageService messageService;

    @Autowired
    public ProfileController(EmailSenderService emailSenderService, MessageService messageService) {
        this.emailSenderService = emailSenderService;
        this.messageService = messageService;
    }

    @GetMapping(value = "messages",produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({Views.MessageUser.class})
    private ResponseEntity<List<Message>> getAllGroupMassages(@RequestParam Long groupId){
        List<Message> massageList = messageService.getByGroup(groupId);
        return new ResponseEntity<>(massageList,HttpStatus.OK);
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
    @JsonView({Views.MessageUser.class})
    public Message change(MessageDto massageRequest){
        return messageService.saveMsg(massageRequest);
  }




}


