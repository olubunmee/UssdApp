package com.olubunmee.USSDApplication;

import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@SpringBootApplication
public class UssdApplication {
    public static void main(String[] args) {
        SpringApplication.run(UssdApplication.class, args);
    }
}
