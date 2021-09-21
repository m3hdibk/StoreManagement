package com.mehdi.storemanagement.service;

import com.mehdi.storemanagement.model.dto.StockHistoryData;
import com.mehdi.storemanagement.model.dto.response.PageResponse;
import com.mehdi.storemanagement.model.dto.response.StockHistoryInformationResponse;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface StockHistoryService {

    StockHistoryInformationResponse getStockHistoryInformation(long productId);

    PageResponse<StockHistoryData> getStockHistoryAnalytics(LocalDate date, LocalTime time, int page, int size);

}