package com.indepthstudy.userservice.adapter.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.indepthstudy.userservice.domain.dto.UserCreatedEvent;
import com.indepthstudy.userservice.domain.service.UserProfileServiceImpl;
import com.indepthstudy.userservice.utils.RabbitMQConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class UserCreatedListener {

    @Autowired
    private UserProfileServiceImpl userProfileService;
    private final Logger logger = LoggerFactory.getLogger(UserCreatedListener.class);
    private final ObjectMapper mapper;

    public UserCreatedListener(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @RabbitListener(queues = RabbitMQConstants.USER_CREATED_QUEUE)
    public void listenMessage(@Payload String payload) {
        try {
            UserCreatedEvent event = mapper.readValue(payload, UserCreatedEvent.class);
            logger.info("Message consumed: {}", event);
            userProfileService.saveUser(event);
        } catch (JsonProcessingException e) {
            logger.error("Erro ao converter mensagem", e);
        }
    }


}
