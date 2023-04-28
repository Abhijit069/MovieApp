package com.moviebookingapp.controller;

// Java Program to Illustrate DemoController Class

import com.moviebookingapp.entity.Book;
import com.moviebookingapp.entity.Movie;
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
    @Autowired KafkaTemplate<String, Movie> kafkaTemplate;

    private static final String TOPIC = "NewTopic";

    Logger logger = LoggerFactory.getLogger(DemoController.class);
    // Annotation
    @PostMapping("/publish")
    public String publishMessage(@RequestBody Movie movie)
    {
        // Sending the message
        kafkaTemplate.send(TOPIC, movie);
        logger.info("Kafka poc working---"+movie.toString());

        return "Published Successfully";
    }
}
