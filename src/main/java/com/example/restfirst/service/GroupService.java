package com.example.restfirst.service;

import com.example.restfirst.model.GroupUni;

public interface GroupService {

    boolean isGroupPresent(Integer number);

    GroupUni getUserGroup(int number);
}
