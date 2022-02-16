package com.mehdi.storemanagement.controller;

import com.mehdi.storemanagement.model.dto.response.StatisticsDashboardResponse;
import com.mehdi.storemanagement.service.PrivilegeService;
import com.mehdi.storemanagement.service.impl.DashboardServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class DashboardController {

    private final DashboardServiceImpl dashboardService;
    private final PrivilegeService privilegeService;


    @GetMapping("/orders")
    @Cacheable("orders")
    public ResponseEntity<?> getAllOrders(
            @RequestParam(defaultValue = "1") int range,
            @RequestParam(defaultValue = "1") int type) {
        log.info("orders");

        StatisticsDashboardResponse response = dashboardService.getOrderStatistics(type,range);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/transactions")
    @Cacheable("transactions")
    public ResponseEntity<?> getAllTransactions(
            @RequestParam(defaultValue = "1") int range,
            @RequestParam(defaultValue = "1") int type) {
        log.info("transactions");

        StatisticsDashboardResponse response = dashboardService.getTransactionsStatistics(type,range);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/clients")
    @Cacheable("clients")
    public ResponseEntity<?> getClientCount(
            @RequestParam(defaultValue = "1") int range) {


        log.info("User Count");
        StatisticsDashboardResponse response = dashboardService.getClientCount(range);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/refresh")
    @CacheEvict(value = { "clients", "transactions", "orders" }, allEntries = true)
    public ResponseEntity<?> refreshData() {
        log.info("clearing cache");
        return ResponseEntity.ok().build();
    }

}
