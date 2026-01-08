package com.in.depthstudy.controller;

import com.in.depthstudy.constants.RabbitMQConstants;
import com.in.depthstudy.dto.PriceDTO;
import com.in.depthstudy.service.RabbitMQService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/price")
public class PriceController {

    private RabbitMQService rabbitMQService;

    public PriceController(RabbitMQService rabbitMQService) {
        this.rabbitMQService = rabbitMQService;
    }

    @PutMapping
    public ResponseEntity changesPrice(@RequestBody PriceDTO request) {

        System.out.println(request.getProductCode());

        rabbitMQService.sendMessage(RabbitMQConstants.PRICE_QUEUE, request);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
