package com.example.restfirst.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;

public class GroupOfStudentsDtoForInsert {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "students")
    private Set<GroupMemberDto> studentSet;

    public GroupOfStudentsDtoForInsert(Long id, Set<GroupMemberDto> studentSet) {
        this.id = id;
        this.studentSet = studentSet;
    }
}
