package com.example.restfirst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ResTfirstApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResTfirstApplication.class, args);
    }

}
