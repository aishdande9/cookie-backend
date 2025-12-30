package com.example.cookie.demo.KafkaProducer;

import com.example.cookie.demo.DTO.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderProducer {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void sendOrderCreated(OrderEvent event) {
        kafkaTemplate.send("order-created-topic", event);
    }
}
