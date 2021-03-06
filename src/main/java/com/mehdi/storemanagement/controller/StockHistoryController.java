package com.mehdi.storemanagement.controller;


import com.mehdi.storemanagement.model.dto.StockHistoryData;
import com.mehdi.storemanagement.model.dto.response.PageResponse;
import com.mehdi.storemanagement.model.dto.response.StockHistoryInformationResponse;
import com.mehdi.storemanagement.model.dto.response.StockHistoryResponse;
import com.mehdi.storemanagement.service.impl.StockHistoryServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@RestController
@RequestMapping("/api/stockHistory")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class StockHistoryController {

    private final StockHistoryServiceImpl stockHistoryService;

    @GetMapping
    public ResponseEntity<?> getAllStocks(
            @RequestParam long productId) {

        StockHistoryInformationResponse response = stockHistoryService.getStockHistoryInformation(productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/analytics")
    public ResponseEntity<?> getStockHistoryAnalytics(
            @RequestParam("localDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(name = "localDateTime", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime time,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {

        PageResponse<StockHistoryData> response = stockHistoryService.getStockHistoryAnalytics(date, time, page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/more")
    public ResponseEntity<?> getStockHistoryByProductIdAndLocation(
            @RequestParam long productId, @RequestParam long locationId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "4") int size) {

        PageResponse<StockHistoryResponse> response =
                stockHistoryService.getStockHistoryByProductIdAndLocation(productId, locationId, page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
