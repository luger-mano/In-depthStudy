package com.microservice.stockconsumer.consumer;

import com.microservice.stockconsumer.constants.RabbitMQConstants;
import com.microservice.stockconsumer.dto.StockDTO;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class StockConsumer {
    @RabbitListener(queuesToDeclare = @Queue(RabbitMQConstants.STOCK_QUEUE),
            containerFactory = "rabbitListenerContainerFactory")
    private void consumer(String message) throws InterruptedException {
        StockDTO stockDTO = new ObjectMapper().readValue(message, StockDTO.class);

        System.out.println(stockDTO.getProductCode());
        System.out.println(stockDTO.getAmount());
        System.out.println("----------------");

//        throw new IllegalArgumentException("Argumento inválido!");
    }
}
