package com.example.restfirst.dto;

import com.example.restfirst.model.Student;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class GroupStudentsDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "number")
    private Integer number;

    @JsonProperty(value = "groupMembers")
    private Set<GroupMemberDto> memberSet;

    public GroupStudentsDto(Long id,Integer number, Set<Student> memberSet) {
        this.id = id;
        this.number = number;
        this.memberSet=new HashSet<>();
        for (Student member: memberSet) {
            this.memberSet.add(new GroupMemberDto(
                    member.getId(),
                    member.getName(),
                    member.getSurname()));
        }
    }

}
