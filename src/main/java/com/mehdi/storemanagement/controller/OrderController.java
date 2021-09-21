package com.mehdi.storemanagement.controller;


import com.mehdi.storemanagement.model.dto.request.OrderRequest;
import com.mehdi.storemanagement.service.impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderServiceImpl;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {

        orderServiceImpl.createOrder(orderRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
