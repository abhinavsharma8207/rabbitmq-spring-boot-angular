package com.springboot;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class BookConsumerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookConsumerServiceApplication.class, args);
    }
}
