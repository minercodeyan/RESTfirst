package com.example.restfirst.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MessageDto {

    private long Id;
    private String description;
    private Long creator;
}
