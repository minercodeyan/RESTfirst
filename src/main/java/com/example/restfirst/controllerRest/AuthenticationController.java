package com.example.restfirst.controllerRest;

import com.example.restfirst.payload.JwtResponse;
import com.example.restfirst.payload.LoginRequest;
import com.example.restfirst.payload.SignupRequest;
import com.example.restfirst.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authUser(@RequestBody @Valid LoginRequest loginRequest) {
        JwtResponse jwtResponse = userService.signInUser(loginRequest);
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> regUser(@RequestBody @Valid SignupRequest signupRequest) {
        if (userService.isUsernameFound(signupRequest.getUsername()))
            return new ResponseEntity<>("Ошибка: такой логин уже есть", HttpStatus.BAD_REQUEST);
        if (userService.isEmailFound(signupRequest.getEmail()))
            return new ResponseEntity<>("Ошибка: такой эмеил уже есть", HttpStatus.BAD_REQUEST);
        userService.CreateNewUser(signupRequest);
        return new ResponseEntity<>(signupRequest, HttpStatus.CREATED);
    }

}
