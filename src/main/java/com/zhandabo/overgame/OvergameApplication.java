package com.zhandabo.overgame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OvergameApplication {

    public static void main(String[] args) {
        SpringApplication.run(OvergameApplication.class, args);
    }

}
