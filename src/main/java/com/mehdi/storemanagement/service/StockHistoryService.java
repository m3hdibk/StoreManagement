package com.mehdi.storemanagement.service;

import com.mehdi.storemanagement.model.dto.StockHistoryData;
import com.mehdi.storemanagement.model.dto.response.PageResponse;
import com.mehdi.storemanagement.model.dto.response.StockHistoryInformationResponse;
import com.mehdi.storemanagement.model.dto.response.StockHistoryResponse;

import java.time.LocalDate;
import java.time.LocalTime;

public interface StockHistoryService {

    StockHistoryInformationResponse getStockHistoryInformation(long productId);

    PageResponse<StockHistoryData> getStockHistoryAnalytics(LocalDate date, LocalTime time, int page, int size);

    PageResponse<StockHistoryResponse> getStockHistoryByProductIdAndLocation(long productId, long locationId,
                                                                     int page, int size);

}