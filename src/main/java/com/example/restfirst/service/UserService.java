package com.example.restfirst.service;

import com.example.restfirst.payload.JwtResponse;
import com.example.restfirst.payload.LoginRequest;
import com.example.restfirst.payload.SignupRequest;
import com.example.restfirst.security.SecurityUser;

import java.util.List;

public interface UserService {

    void createNewUser(SignupRequest signupRequest);

    Boolean isUsernameFound(String username);

    Boolean isEmailFound(String email);

    JwtResponse signInUser(LoginRequest loginRequest);

}
