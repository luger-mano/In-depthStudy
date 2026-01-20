package com.indepth.authservice.infra.publish;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.indepth.authservice.domain.ports.out.AuthPublisherPort;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQPublisher implements AuthPublisherPort {

    private final RabbitTemplate template;

    private final ObjectMapper mapper;

    public RabbitMQPublisher(RabbitTemplate template, ObjectMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    @Override
    public void publish(String queueName, Object message) {
        try{
            String jsonMessage = mapper.writeValueAsString(message);
            this.template.convertAndSend(queueName, jsonMessage);
        }catch (AmqpException e){
            throw new AmqpException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
