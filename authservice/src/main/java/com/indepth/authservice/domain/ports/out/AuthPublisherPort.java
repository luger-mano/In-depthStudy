package com.indepth.authservice.domain.ports.out;

public interface AuthPublisherPort {
    void publish(String queueName, Object message);
}
