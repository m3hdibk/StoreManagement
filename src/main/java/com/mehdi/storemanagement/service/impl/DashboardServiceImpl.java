package com.mehdi.storemanagement.service.impl;


import com.mehdi.storemanagement.model.dto.response.StatisticsDashboardResponse;
import com.mehdi.storemanagement.repository.UserRepository;
import com.mehdi.storemanagement.repository.OrderRepository;
import com.mehdi.storemanagement.repository.StockHistoryRepository;
import com.mehdi.storemanagement.service.AuthorizationService;
import com.mehdi.storemanagement.service.DashboardService;
import com.mehdi.storemanagement.util.FilterUtils;
import com.mehdi.storemanagement.util.PrivilegeConstants;
import com.mehdi.storemanagement.util.StockUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

@Service
@Slf4j
public record DashboardServiceImpl(OrderRepository orderRepository, StockHistoryRepository stockHistoryRepository,
                                   UserRepository userRepository, AuthorizationService authorizationService)
        implements DashboardService {

    @Override
    public StatisticsDashboardResponse getOrderStatistics(int type, int range) {
        LocalDateTime dateTime = LocalDate.now().atStartOfDay();
        LocalDateTime start;
        LocalDateTime end;
        if (FilterUtils.DateType.Month.getId() == range) {
            start = dateTime.withDayOfMonth(1);
            end = dateTime.with(TemporalAdjusters.lastDayOfMonth());
        } else {
            start = dateTime.with(DayOfWeek.MONDAY);
            end = dateTime.with(DayOfWeek.SUNDAY);
        }

        log.info("now : " + dateTime);
        log.info("start : " + start);
        log.info("end : " + end);

        int sumOrders;
        double amountOrders;
        StatisticsDashboardResponse orderDashboardResponse = new StatisticsDashboardResponse();
        orderDashboardResponse.setStartDate(start.toLocalDate());
        orderDashboardResponse.setEndDate(end.toLocalDate());
        if (FilterUtils.DashboardSumType.AMOUNT.getId() == type) {
            amountOrders = orderRepository.sumAllByDateBetween(start, end);
            orderDashboardResponse.setResult(amountOrders);
        } else {
            sumOrders = orderRepository.countAllByDateBetween(start, end);
            orderDashboardResponse.setResult(sumOrders);
        }

        return orderDashboardResponse;
    }

    @Override
    public StatisticsDashboardResponse getTransactionsStatistics(int type, int range) {
        LocalDate dateTime = LocalDate.now();
        LocalDate start;
        LocalDate end;
        if (FilterUtils.DateType.Month.getId() == range) {
            start = dateTime.withDayOfMonth(1);
            end = dateTime.with(TemporalAdjusters.lastDayOfMonth());
        } else {
            start = dateTime.with(DayOfWeek.MONDAY);
            end = dateTime.with(DayOfWeek.SUNDAY);
        }

        log.info("now : " + dateTime);
        log.info("start : " + start);
        log.info("end : " + end);
        // TODO  finish this
        int sumOrders;
        double amountOrders;
        StatisticsDashboardResponse orderDashboardResponse = new StatisticsDashboardResponse();
        orderDashboardResponse.setStartDate(start);
        orderDashboardResponse.setEndDate(end);
        if (StockUtils.StockTransactionType.SELL.getId() == type) {
            amountOrders = stockHistoryRepository.sumAllByDateBetween(start, end, type);
            orderDashboardResponse.setResult(amountOrders);
        } else {
            sumOrders = stockHistoryRepository.countAllByDateBetweenAndTransactionType(start, end, type);
            orderDashboardResponse.setResult(sumOrders);
        }

        return orderDashboardResponse;

    }

    @Override
    public StatisticsDashboardResponse getClientCount(int range) {
        authorizationService.checkPrivilege(PrivilegeConstants.DASHBOARD_VIEW);
        LocalDate dateTime = LocalDate.now();
        LocalDate start;
        LocalDate end;
        if (FilterUtils.DateType.Month.getId() == range) {
            start = dateTime.withDayOfMonth(1);
            end = dateTime.with(TemporalAdjusters.lastDayOfMonth());
        } else {
            start = dateTime.with(DayOfWeek.MONDAY);
            end = dateTime.with(DayOfWeek.SUNDAY);
        }

        log.info("now : " + dateTime);
        log.info("start : " + start);
        log.info("end : " + end);
        // TODO  finish this
        int sumOrders;
        double amountOrders;
        StatisticsDashboardResponse orderDashboardResponse = new StatisticsDashboardResponse();
        orderDashboardResponse.setStartDate(start);
        orderDashboardResponse.setEndDate(end);
        amountOrders = userRepository.count();
        orderDashboardResponse.setResult(amountOrders);

        return orderDashboardResponse;
    }

}
