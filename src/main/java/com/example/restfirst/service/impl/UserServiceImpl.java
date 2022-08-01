package com.example.restfirst.service.impl;

import com.example.restfirst.model.ERole;
import com.example.restfirst.model.GroupUni;
import com.example.restfirst.model.Role;
import com.example.restfirst.model.User;
import com.example.restfirst.payload.JwtResponse;
import com.example.restfirst.payload.LoginRequest;
import com.example.restfirst.payload.SignupRequest;
import com.example.restfirst.repo.GroupRepo;
import com.example.restfirst.repo.RoleRepo;
import com.example.restfirst.repo.UserRepo;
import com.example.restfirst.security.SecurityUser;
import com.example.restfirst.security.jwt.JwtUtils;
import com.example.restfirst.service.UserService;
import org.apache.catalina.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public void createNewUser(SignupRequest signupRequest) {
        User user = new User(signupRequest.getUsername(),passwordEncoder.encode(signupRequest.getPassword()),
                signupRequest.getEmail());
        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepo.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepo.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role"+ role + "is not found."));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleRepo.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role"+ role + "is not found."));
                        roles.add(modRole);
                        break;
                    default:
                        Role userRole = roleRepo.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role"+ role + "is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoleSet(roles);
        userRepo.save(user);
    }

    @Override
    public JwtResponse signInUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        List<String> roles = securityUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        Optional<User> user = userRepo.findByUsername(securityUser.getUsername());
        return new JwtResponse(
                jwt,securityUser.getId(),securityUser.getUsername(),
                securityUser.getEmail(),roles,
                user.get().getStudent() != null ? user.get().getStudent().getGroupUni().getGroupNumber() : 0);
    }

    @Override
    public Boolean isUsernameFound(String username) {
        return userRepo.findByUsername(username).isPresent();
    }

    @Override
    public Boolean isEmailFound(String email) {
        return userRepo.findByEmail(email).isPresent();
    }


}
