package com.microservice.stockconsumer.exceptions;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.util.ErrorHandler;

public class TreatmentErrorHandler implements ErrorHandler {


    @Override
    public void handleError(Throwable t) {
        String queueName = ((ListenerExecutionFailedException) t).getFailedMessage().getMessageProperties().getConsumerQueue();
        System.out.println(queueName);

        String message = new String(((ListenerExecutionFailedException) t).getFailedMessage().getBody());
        System.out.println(message);

        System.out.println(t.getCause().getMessage());

        //Logar no ElasticSearch
        //Logar no Cloud Watch(AWS)

//        throw new AmqpRejectAndDontRequeueException("Não deve retornar a fila");
    }
}
