package com.moviebookingapp.configuration;
import com.moviebookingapp.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    @KafkaListener(topics = "NewTopic",
            groupId = "group_id",
            containerFactory = "bookListener")

    public void  consume(Movie movie) {
        logger.info("Consuming from KafkaConsumer "+movie.toString());
    }
}
