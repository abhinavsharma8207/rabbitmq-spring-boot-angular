package com.springboot.controller;

import com.springboot.dto.BookDto;
import com.springboot.service.RabbitMqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/")
public class BookProducerController {


    private RabbitMqSender rabbitMqSender;

    @Autowired
    public BookProducerController(RabbitMqSender rabbitMqSender) {
        this.rabbitMqSender = rabbitMqSender;
    }

    @Value("${app.message}")
    private String message;

    @PostMapping(value = "book")
    public String publishBookDetails(@RequestBody BookDto book) {
        rabbitMqSender.send(book);
        return message;
    }
}

