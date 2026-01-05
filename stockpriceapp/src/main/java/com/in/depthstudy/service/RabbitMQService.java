package com.in.depthstudy.service;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper mapper;

    public void sendMessage(String queueName, Object message){
        try{
            String jsonMessage = mapper.writeValueAsString(message);
            this.rabbitTemplate.convertAndSend(queueName, jsonMessage);

        } catch (AmqpException e) {
            throw new RuntimeException(e);
        }
    }
}
