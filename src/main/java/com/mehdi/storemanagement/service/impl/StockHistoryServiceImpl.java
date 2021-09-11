package com.mehdi.storemanagement.service.impl;


import com.mehdi.storemanagement.model.StockHistory;
import com.mehdi.storemanagement.model.dto.StockHistoryData;
import com.mehdi.storemanagement.model.dto.response.PageResponse;
import com.mehdi.storemanagement.repository.StockHistoryRepository;
import com.mehdi.storemanagement.service.StockHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public record StockHistoryServiceImpl(StockHistoryRepository stockHistoryRepository) implements StockHistoryService {

    @Override
    public PageResponse<StockHistoryData> getStockHistoryInformation(long stockId, int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<StockHistory> stockHistoryPage = stockHistoryRepository.findByStockId(stockId, paging);

        List<StockHistoryData> stockHistoryDataList = stockHistoryPage.getContent().stream()
                .map(StockHistory::convertToData)
                .collect(Collectors.toList());

        return new PageResponse<>(stockHistoryDataList, stockHistoryPage.getNumber(),
                stockHistoryPage.getTotalElements(),
                stockHistoryPage.getTotalPages());
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
}
