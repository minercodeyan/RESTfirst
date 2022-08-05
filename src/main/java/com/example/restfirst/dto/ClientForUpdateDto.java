package com.example.restfirst.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientForUpdateDto {
    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "surname")
    private String surname;


}
