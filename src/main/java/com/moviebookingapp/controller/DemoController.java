package com.moviebookingapp.controller;

// Java Program to Illustrate DemoController Class

import com.moviebookingapp.entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// Annotation
@RestController

// Class
public class DemoController {

    // Autowiring Kafka Template
    @Autowired KafkaTemplate<String, Book> kafkaTemplate;

    private static final String TOPIC = "NewTopic";

    Logger logger = LoggerFactory.getLogger(DemoController.class);
    // Annotation
    @PostMapping("/publish")
    public String publishMessage(@RequestBody Book book)
    {
        // Sending the message
        kafkaTemplate.send(TOPIC, book);
        logger.info("Kafka poc working---"+book.toString());

        return "Published Successfully";
    }
}
