package com.example.restfirst.service;

import com.example.restfirst.dto.GroupStudentsDto;

public interface GroupService {

    boolean isGroupPresent(Integer number);

    GroupStudentsDto getUserGroup(int number);
}
