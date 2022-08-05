package com.example.restfirst.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class StudentDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "surname")
    private String surname;

    @JsonProperty(value = "patronymic")
    private String patronymic;

    @JsonProperty(value = "group_id")
    private Long group_id;

    @JsonProperty(value = "sex")
    private String sex;


}
