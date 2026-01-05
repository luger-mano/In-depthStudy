package com.microservice.stockconsumer.consumer;

import dto.StockDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import constants.RabbitMQConstants;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class StockConsumer {
    @RabbitListener(queues = RabbitMQConstants.STOCK_QUEUE,
            containerFactory = "rabbitListenerContainerFactory")
    private void consumer(String message) throws InterruptedException {
        StockDTO stockDTO = new ObjectMapper().readValue(message, StockDTO.class);

        System.out.println(stockDTO.getProductCode());
        System.out.println(stockDTO.getAmount());
        System.out.println("----------------");

        throw new IllegalArgumentException("Argumento inválido!");
    }
}
