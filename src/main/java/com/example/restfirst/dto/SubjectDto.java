package com.example.restfirst.dto;

import com.example.restfirst.model.modelForTimeTable.Subject;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SubjectDto {
    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "type")
    private String type;

    public SubjectDto(Subject subject){
        if(subject==null)
        {
            this.name="нет занятий";
            this.type="";
        }
        else
        {
            this.name = subject.getSubjectName();
            this.type = subject.getType();
        }
    }

}
