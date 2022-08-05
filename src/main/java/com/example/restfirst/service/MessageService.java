package com.example.restfirst.service;

import com.example.restfirst.dto.MessageDto;
import com.example.restfirst.model.communicationEntities.Message;

import java.util.List;

public interface MessageService {

    Message saveMsg(MessageDto messageDto);

    List<Message> getByGroup(Long message);
}
