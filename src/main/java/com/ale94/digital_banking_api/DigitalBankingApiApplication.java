package com.ale94.digital_banking_api;

import com.ale94.digital_banking_api.infraestructure.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DigitalBankingApiApplication implements CommandLineRunner {
    @Autowired
    private UserServiceImpl userService;

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankingApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(userService.accountNumberGenerator());
    }
}
