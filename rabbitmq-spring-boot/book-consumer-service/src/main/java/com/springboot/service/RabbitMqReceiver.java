package com.springboot.service;

import com.springboot.config.Mapper;
import com.springboot.dto.BookDto;
import com.springboot.model.Book;
import com.springboot.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqReceiver implements RabbitListenerConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMqReceiver.class);

    @Autowired
    Mapper mapper;

    @Autowired
    BookRepository bookRepository;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(BookDto book) {
        logger.info("Book Details Received is.. " + book);
        Book bookModel = mapper.map(book, Book.class);
        bookRepository.save(bookModel);

    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}
