package com.example.restfirst.controllerRest;

import com.example.restfirst.dto.GroupOfStudentsDtoForInsert;
import com.example.restfirst.security.SecurityUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashSet;


@RestController
@RequestMapping("api/v1/test")
@PreAuthorize("hasRole('MODERATOR')")
public class TestController {



    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getServerTime(@AuthenticationPrincipal SecurityUser securityUser) {
        return new ResponseEntity<>(new GroupOfStudentsDtoForInsert(10L,new HashSet<>()),HttpStatus.OK);
    }

}
