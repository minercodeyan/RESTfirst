package com.example.restfirst.controllerRest;

import com.example.restfirst.service.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/timetable")
public class TimeTableController {


    private final TimeTableService timeTableService;

    @Autowired
    public TimeTableController( TimeTableService timeTableService) {

        this.timeTableService = timeTableService;
    }

    @GetMapping( "{number}")
    public ResponseEntity<?> getGroupTimeTable(@PathVariable("number") Integer number){
        return new ResponseEntity<>(timeTableService.getGroupTimeTable(number),HttpStatus.OK);
    }
}
