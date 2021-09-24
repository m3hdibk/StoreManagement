package com.mehdi.storemanagement.service.impl;


import com.mehdi.storemanagement.model.StockHistory;
import com.mehdi.storemanagement.model.dto.SimpleValue;
import com.mehdi.storemanagement.model.dto.StockHistoryData;
import com.mehdi.storemanagement.model.dto.response.PageResponse;
import com.mehdi.storemanagement.model.dto.response.StockHistoryInformationResponse;
import com.mehdi.storemanagement.model.dto.response.StockHistoryResponse;
import com.mehdi.storemanagement.repository.StockHistoryRepository;
import com.mehdi.storemanagement.service.StockHistoryService;
import com.mehdi.storemanagement.util.StockUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public record StockHistoryServiceImpl(StockHistoryRepository stockHistoryRepository) implements StockHistoryService {

    public static final int DEFAULT_LOCATION_LIMIT = 3;
    public static final int DEFAULT_STOCK_HISTORY_LIMIT = 4;

    @Override
    public StockHistoryInformationResponse getStockHistoryInformation(long productId) {

        List<StockHistory> stockHistoryPage = stockHistoryRepository.findByProductId(productId,
                DEFAULT_LOCATION_LIMIT, DEFAULT_STOCK_HISTORY_LIMIT);

        List<StockHistoryData> list = stockHistoryPage.stream()
                .map(StockHistory::convertToData)
                .collect(Collectors.toList());

        StockHistoryInformationResponse stockHistoryInformationResponse = new StockHistoryInformationResponse();
        stockHistoryInformationResponse.build(list);
        return stockHistoryInformationResponse;
    }

    @Override
    public PageResponse<StockHistoryData> getStockHistoryAnalytics(LocalDate date, LocalTime time, int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<StockHistory> stockHistoryPage;
        if (time == null) {
            stockHistoryPage = stockHistoryRepository.findByDate(date, paging);
        } else {
            stockHistoryPage = stockHistoryRepository.findByDateAndTime(date, time, paging);
        }

        List<StockHistoryData> stockHistoryDataList = stockHistoryPage.getContent().stream()
                .map(StockHistory::convertToData)
                .collect(Collectors.toList());

        return new PageResponse<>(stockHistoryDataList, stockHistoryPage.getNumber(),
                stockHistoryPage.getTotalElements(),
                stockHistoryPage.getTotalPages());
    }

    @Override
    public PageResponse<StockHistoryResponse> getStockHistoryByProductIdAndLocation(long productId, long locationId, int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<StockHistory> stockHistoryPage = stockHistoryRepository.findByProductIdAndLocation(productId,
                locationId, paging);
        List<StockHistoryData> stockHistoryDataList = stockHistoryPage.getContent().stream()
                .map(StockHistory::convertToData)
                .collect(Collectors.toList());
        List<StockHistoryResponse> response = StockUtils.getStockHistoryResponseList(stockHistoryDataList);

        return new PageResponse<>(response, stockHistoryPage.getNumber(),
                stockHistoryPage.getTotalElements(),
                stockHistoryPage.getTotalPages());
    }


}
