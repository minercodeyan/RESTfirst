package com.example.restfirst.cfg;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class EncriptionConfig {
    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(8);
    }
}
