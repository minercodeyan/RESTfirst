package com.example.restfirst.controllerRest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {


    @PostMapping(value="/email", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendMail(@RequestBody Map<String,String> params) {

        return new ResponseEntity<>( HttpStatus.CREATED);
    }




}


