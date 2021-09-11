package com.mehdi.storemanagement.service;

import com.mehdi.storemanagement.model.dto.StockHistoryData;
import com.mehdi.storemanagement.model.dto.response.PageResponse;

import java.time.LocalDate;
import java.time.LocalTime;

public interface StockHistoryService {

    PageResponse<StockHistoryData> getStockHistoryInformation(long stockId, int page, int size);

    PageResponse<StockHistoryData> getStockHistoryAnalytics(LocalDate date, LocalTime time, int page, int size);

}