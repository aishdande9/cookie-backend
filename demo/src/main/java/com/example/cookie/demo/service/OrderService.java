package com.example.cookie.demo.service;

import com.example.cookie.demo.DTO.OrderEvent;
import com.example.cookie.demo.KafkaProducer.OrderProducer;
import com.example.cookie.demo.Repository.OrderRepository;
import com.example.cookie.demo.model.Order;
import com.example.cookie.demo.model.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
     @Autowired
    private OrderRepository orderRepository;
     @Autowired
     private OrderProducer orderProducer;

     public Order createOrder(Order order){
         //set initial status
         order.setOrderStatus(OrderStatus.CREATED);

         //save the order in the database

         // Save to DB
         Order savedOrder = orderRepository.save(order);

         // Publish Kafka event
         OrderEvent event = new OrderEvent(savedOrder.getId(), savedOrder.getTotalAmount());
         orderProducer.sendOrderCreated(event);

         return savedOrder;

     }
}
