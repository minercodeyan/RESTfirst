package com.example.restfirst.controllerRest;


import com.example.restfirst.service.GroupService;
import com.example.restfirst.service.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/timetable")
public class TimeTableController {

    private final GroupService groupService;
    private final TimeTableService timeTableService;

    @Autowired
    public TimeTableController(GroupService groupService, TimeTableService timeTableService) {
        this.groupService = groupService;
        this.timeTableService = timeTableService;
    }


    @GetMapping( "{number}")
    public ResponseEntity<?> getGroupTimeTable(@PathVariable("number") Integer number){
        if(number == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if(!groupService.isGroupPresent(number))
            return new ResponseEntity<>("не найдена",HttpStatus.NOT_FOUND);
        List<List<String>> response = timeTableService.getGroupTimeTable(number);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


}
