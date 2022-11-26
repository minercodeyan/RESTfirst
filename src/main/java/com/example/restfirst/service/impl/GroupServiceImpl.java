package com.example.restfirst.service.impl;

import com.example.restfirst.dto.GroupStudentsDto;
import com.example.restfirst.exceptions.NotFoundException;
import com.example.restfirst.model.GroupUni;
import com.example.restfirst.repo.GroupRepo;
import com.example.restfirst.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepo groupRepo;

    @Autowired
    public GroupServiceImpl(GroupRepo groupRepo) {
        this.groupRepo = groupRepo;
    }


    @Override
    public GroupStudentsDto getUserGroup(int number) {
        GroupUni gr = groupRepo.findByGroupNumber(number).orElseThrow(() -> new NotFoundException("group"));
        return new GroupStudentsDto(gr.getId(),gr.getGroupNumber(),gr.getStudentSet());
    }


}
