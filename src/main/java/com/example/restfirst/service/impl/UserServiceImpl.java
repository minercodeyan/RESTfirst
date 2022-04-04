package com.example.restfirst.service.impl;

import com.example.restfirst.model.ERole;
import com.example.restfirst.model.Role;
import com.example.restfirst.model.User;
import com.example.restfirst.payload.SignupRequest;
import com.example.restfirst.repo.RoleRepo;
import com.example.restfirst.repo.UserRepo;
import com.example.restfirst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void CreateNewUser(SignupRequest signupRequest) {
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
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepo.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepo.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoleSet(roles);
        userRepo.save(user);
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
