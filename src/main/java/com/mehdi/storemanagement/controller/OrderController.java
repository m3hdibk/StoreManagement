package com.mehdi.storemanagement.controller;


import com.mehdi.storemanagement.model.dto.StockHistoryData;
import com.mehdi.storemanagement.model.dto.request.OrderRequest;
import com.mehdi.storemanagement.model.dto.response.PageResponse;
import com.mehdi.storemanagement.service.OrderService;
import com.mehdi.storemanagement.service.impl.OrderServiceImpl;
import com.mehdi.storemanagement.service.impl.StockHistoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;


@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderServiceImpl;

    @PostMapping
    public ResponseEntity<?> getAllStocks(@RequestBody OrderRequest orderRequest) {

        orderServiceImpl.createOrder(orderRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
