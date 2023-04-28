package com.moviebookingapp.configuration;

import com.moviebookingapp.controller.DemoController;
import com.moviebookingapp.entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

// Annotation
@Component

// Class
public class KafkaConsumer {

    Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    @KafkaListener(topics = "NewTopic",
            groupId = "group_id",
            containerFactory = "bookListener")


    // Method
    public void
    consume(Book book)
    {
        // Print statement
        logger.info("Consuming from KafkaConsumer "+book.toString());
        System.out.println("message = " + book);
    }
}
