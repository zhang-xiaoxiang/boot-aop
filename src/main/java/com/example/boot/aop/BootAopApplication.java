package com.example.boot.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootAopApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootAopApplication.class, args);
        System.out.println("http://localhost:8081/getUser?id=1");
    }

}
