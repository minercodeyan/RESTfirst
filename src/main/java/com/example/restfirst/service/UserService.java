package com.example.restfirst.service;

import com.example.restfirst.payload.SignupRequest;

public interface UserService {
    void CreateNewUser(SignupRequest signupRequest);
    Boolean isUsernameFound(String username);
    Boolean isEmailFound(String email);
}
