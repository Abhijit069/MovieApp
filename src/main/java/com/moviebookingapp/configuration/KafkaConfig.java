package com.moviebookingapp.configuration;

// Importing required classes
import java.util.HashMap;
import java.util.Map;

import com.moviebookingapp.entity.Book;
import com.moviebookingapp.entity.Movie;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;


// Annotation
@Configuration

// Class
public class KafkaConfig {

    // Annotation
    @Bean

    // Method
    public ProducerFactory<String, Movie> producerFactory()
    {
         Map<String, Object> config = new HashMap<>();
        // 127.0.0.1:9092 is the default port number for
        // kafka
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "127.0.0.1:9092");
        config.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        config.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }


    @Bean
    public KafkaTemplate kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ConsumerFactory<String, Movie> consumerFactory()
    {

        // Creating a map of string-object type
        Map<String, Object> config = new HashMap<>();

        // Adding the Configuration
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "127.0.0.1:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,
                "group_id");
        config.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        config.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class);

        // Returning message in JSON format
        return new DefaultKafkaConsumerFactory<>(
                config, new StringDeserializer(),
                new JsonDeserializer<>(Movie.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,
            Movie>
    bookListener()
    {
        ConcurrentKafkaListenerContainerFactory<
                String, Movie> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }

}
