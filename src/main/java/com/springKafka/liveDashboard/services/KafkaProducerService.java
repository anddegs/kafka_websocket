package com.springKafka.liveDashboard.services;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 10000)
    public void sendMessage() {
        Random random = new Random();
        int min = 60;
        int max = 100;

        // Generate a random integer between 10 (inclusive) and 100 (inclusive)
        int randomInt = random.nextInt(max - min + 1) + min;
         kafkaTemplate.send("livetemperature", String.valueOf(randomInt));
    }

}
