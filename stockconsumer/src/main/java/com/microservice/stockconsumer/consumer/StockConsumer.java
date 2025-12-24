package com.microservice.stockconsumer.consumer;

import dto.StockDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import constants.RabbitMQConstants;
import org.springframework.stereotype.Component;

@Component
public class StockConsumer {
    @RabbitListener(queues = RabbitMQConstants.STOCK_QUEUE,
            containerFactory = "rabbitListenerContainerFactory")
    private void consumer(StockDTO dto){
        System.out.println(dto.getProductCode());
        System.out.println(dto.getAmount());
        System.out.println("----------------");
    }
}
