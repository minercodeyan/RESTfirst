package com.example.restfirst.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;


@Getter
@NoArgsConstructor
public class EmailDto {

    @JsonProperty(value = "subject")
    private String subject;

    @JsonProperty(value = "body")
    private String body;

    @NotNull
    @JsonProperty(value = "toEmail")
    private String toEmail;

}
