package com.mehdi.storemanagement.service;

import com.mehdi.storemanagement.model.dto.response.StatisticsDashboardResponse;

public interface DashboardService {

    StatisticsDashboardResponse getOrderStatistics(int type, int range);
    StatisticsDashboardResponse getTransactionsStatistics(int type, int range);
    StatisticsDashboardResponse getClientCount(int range);
}
