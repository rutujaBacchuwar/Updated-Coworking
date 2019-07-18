package com.stackroute.kafka.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class Producer {

    @Autowired
    Booking booking;

    @Value("${kafka.topic.json}")
    private String jsonTopic;

    private static final Logger LOGGER =
            LoggerFactory.getLogger(Producer.class);

    @Autowired
    private KafkaTemplate<String, Booking> kafkaTemplate;

    public void send(Booking booking) {
        LOGGER.info("sending data from booking service='{}'", booking.toString());
        kafkaTemplate.send(jsonTopic,booking );
    }

}