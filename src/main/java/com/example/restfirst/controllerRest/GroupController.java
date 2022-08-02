package com.example.restfirst.controllerRest;

import com.example.restfirst.model.GroupUni;
import com.example.restfirst.model.User;
import com.example.restfirst.security.SecurityUser;
import com.example.restfirst.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/group")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping(value = "{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupUni> getUserGroup(@AuthenticationPrincipal SecurityUser user, @PathVariable int number) {
        if(user!=null){
            GroupUni groupUni = groupService.getUserGroup(number);
            return new ResponseEntity<>(groupUni, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }



}
