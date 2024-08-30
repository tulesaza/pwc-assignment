package com.github.tulesaza.pwcassignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class PwcAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(PwcAssignmentApplication.class, args);
    }

}
