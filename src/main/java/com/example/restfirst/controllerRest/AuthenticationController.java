package com.example.restfirst.controllerRest;

import com.example.restfirst.model.User;
import com.example.restfirst.payload.JwtResponse;
import com.example.restfirst.payload.LoginRequest;
import com.example.restfirst.payload.SignupRequest;
import com.example.restfirst.security.SecurityUser;
import com.example.restfirst.security.jwt.JwtUtils;
import com.example.restfirst.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

   private final AuthenticationManager authenticationManager;

   private final JwtUtils jwtUtils;

   private final UserService userService;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    @PostMapping(value = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authUser(@RequestBody @Valid LoginRequest loginRequest)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        List<String> roles = securityUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new ResponseEntity<>(new JwtResponse(jwt,securityUser.getId(),securityUser.getUsername(),securityUser.getEmail(),roles), HttpStatus.OK);
    }

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> regUser(@RequestBody @Valid SignupRequest signupRequest)
    {
       if(userService.isUsernameFound(signupRequest.getUsername()))
           return new ResponseEntity<>("Ошибка: логин такой уже есть", HttpStatus.BAD_REQUEST);
        if(userService.isEmailFound(signupRequest.getEmail()))
            return new ResponseEntity<>("Ошибка: емеил уже есть", HttpStatus.BAD_REQUEST);
        userService.CreateNewUser(signupRequest);
        return new ResponseEntity<>("sussess",HttpStatus.CREATED);
    }


}
