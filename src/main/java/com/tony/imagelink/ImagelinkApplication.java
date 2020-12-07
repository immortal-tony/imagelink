package com.tony.imagelink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ImagelinkApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImagelinkApplication.class, args);
    }
}
