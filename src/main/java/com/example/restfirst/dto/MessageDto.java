package com.example.restfirst.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
public class MessageDto {

    private long Id;

    @NotNull
    private String description;

    @NotNull
    private Long creator;
}
