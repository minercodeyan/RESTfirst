package com.example.restfirst.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatManager {
    private final ObjectMapper mapper;

    @Autowired
    public ChatManager( ObjectMapper mapper) {
        this.mapper = mapper;
    }
}
