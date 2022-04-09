package com.example.restfirst.service.impl;

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
    public boolean isGroupPresent(Integer number) {
        return groupRepo.findByGroupNumber(number).isPresent();
    }
}
