package com.example.cookie.demo.service;

import com.example.cookie.demo.DTO.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryConsumer {
    @KafkaListener(topics = "order-created-topic", groupId = "inventory-group")
    public void consumeOrder(OrderEvent event) {
        System.out.println("Inventory received order: " + event.getOrderId());
        // simulate inventory check
    }
}

