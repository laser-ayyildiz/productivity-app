package com.example.producer;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final NewTopic createTodoEventTopic;

    public void sendMessage(String msg) {
        kafkaTemplate.send(createTodoEventTopic.name(), msg);
    }
}
