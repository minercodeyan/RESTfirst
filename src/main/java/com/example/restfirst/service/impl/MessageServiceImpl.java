package com.example.restfirst.service.impl;

import com.example.restfirst.dto.MessageDto;
import com.example.restfirst.model.User;
import com.example.restfirst.model.communicationEntities.Message;
import com.example.restfirst.repo.MessageRepo;
import com.example.restfirst.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepo messageRepo;

    @Autowired
    public MessageServiceImpl(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @Override
    public Message saveMsg(MessageDto messageRequest) {
        Message massageResp = new Message();
        massageResp.setDate(new Date());
        massageResp.setDescription(messageRequest.getDescription());
        massageResp.setUser(new User(messageRequest.getCreator()));
        return messageRepo.save(massageResp);
    }

    @Override
    public List<Message> getByGroup(Long group) {
        return messageRepo.findByGroup(group);
    }
}
