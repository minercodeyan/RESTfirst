package com.example.restfirst.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MessageDto {

    private long Id;
    private String description;
    private Long creator;
}
