package com.example.restfirst.dto;


import com.example.restfirst.model.TimeTable;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TimeTableResponseDto {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    public TimeTableResponseDto(Long i,String l){
        this.id = i;
        this.name=l;
    }

    public TimeTableResponseDto(TimeTable timeTable){
        this.id=timeTable.getId();
        if(timeTable.getSubject()==null)
            this.name="нет занятий";
        else
            this.name=timeTable.getSubject().getSubjectName();
    }

}
