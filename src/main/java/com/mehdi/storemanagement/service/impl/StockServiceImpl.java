package com.mehdi.storemanagement.service.impl;

import com.mehdi.storemanagement.exception.SchemeNotFoundException;
import com.mehdi.storemanagement.model.Scheme;
import com.mehdi.storemanagement.model.Stock;
import com.mehdi.storemanagement.model.dto.*;
import com.mehdi.storemanagement.model.dto.request.StockUpdateRequest;
import com.mehdi.storemanagement.model.dto.response.PageResponse;
import com.mehdi.storemanagement.model.dto.response.ProductResponse;
import com.mehdi.storemanagement.model.dto.response.StockLocationsResponse;
import com.mehdi.storemanagement.model.dto.response.StockSumQuantityResponse;
import com.mehdi.storemanagement.repository.ProductRepository;
import com.mehdi.storemanagement.repository.SchemeRepository;
import com.mehdi.storemanagement.repository.StockHistoryRepository;
import com.mehdi.storemanagement.repository.StockRepository;
import com.mehdi.storemanagement.service.StockService;
import com.mehdi.storemanagement.util.ResponseConverterUtils;
import com.mehdi.storemanagement.util.SchemeUtils;
import com.mehdi.storemanagement.util.StockUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public record StockServiceImpl(StockRepository stockRepository, ProductRepository productRepository,
                               StockHistoryRepository stockHistoryRepository, SchemeRepository schemeRepository)
        implements StockService {


    @Override
    public PageResponse<StockSumQuantityResponse> getAllStockInformation(int page, int size) {

        Pageable paging = PageRequest.of(page, size);
        Page<StockSumQuantity> pageStock = stockRepository.findStockByWithQuantitySum(paging);

        List<StockSumQuantityResponse> products = pageStock.getContent().stream()
                .map(stockSumQuantity -> {
                    StockSumQuantityResponse stockSumQuantityResponse = new StockSumQuantityResponse();
                    stockSumQuantityResponse.setSumQuantity(stockSumQuantity.getSumQuantity());
                    ProductData productData = stockSumQuantity.getProduct().convertToData();
                    ProductResponse productResponse = ResponseConverterUtils.convertToProductResponse(productData);
                    stockSumQuantityResponse.setProductData(productResponse);
                    return stockSumQuantityResponse;
                })
                .collect(Collectors.toList());

        return new PageResponse<>(products, pageStock.getNumber(),
                pageStock.getTotalElements(),
                pageStock.getTotalPages());
    }

    @Override
    public PageResponse<StockSumQuantityResponse> searchStockByProduct(String searchInput, int page, int size) {

        Pageable paging = PageRequest.of(page, size);
        Page<StockSumQuantity> pageStock = stockRepository.findStockByProductWithQuantitySum(searchInput, paging);

        List<StockSumQuantityResponse> products = pageStock.getContent().stream()
                .map(stockSumQuantity -> {
                    StockSumQuantityResponse stockSumQuantityResponse = new StockSumQuantityResponse();
                    stockSumQuantityResponse.setSumQuantity(stockSumQuantity.getSumQuantity());
                    ProductData productData = stockSumQuantity.getProduct().convertToData();
                    ProductResponse productResponse = ResponseConverterUtils.convertToProductResponse(productData);
                    stockSumQuantityResponse.setProductData(productResponse);
                    return stockSumQuantityResponse;
                })
                .collect(Collectors.toList());

        return new PageResponse<>(products, pageStock.getNumber(),
                pageStock.getTotalElements(),
                pageStock.getTotalPages());
    }




    @Override
    public void updateStock(StockUpdateRequest stockUpdateRequest, long stockId) {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        StockData stockData = getStockData(stockId);
        int transactionType = stockUpdateRequest.getTransactionType();
        if (StockUtils.StockTransactionType.BUY.getId() == transactionType) {
            stockData.setQuantity(stockData.getQuantity() + stockUpdateRequest.getQuantity());
            createStockHistory(stockUpdateRequest, stockData, transactionType, currentDate, currentTime);
        } else if (StockUtils.StockTransactionType.SELL.getId() == transactionType) {
            stockData.setQuantity(stockData.getQuantity() - stockUpdateRequest.getQuantity());
            createStockHistory(stockUpdateRequest, stockData, transactionType, currentDate, currentTime);
        } else if (StockUtils.StockTransactionType.TRANSFER.getId() == transactionType) {
            Optional<Stock> optionalStockTrans =
                    stockRepository.findByProductAndLocation(stockData.getProduct().getId(), stockUpdateRequest.getNewLocation());
            StockData stockDataTrans;
            if (optionalStockTrans.isEmpty()) {
                stockDataTrans = getNewStockDataTrans(stockUpdateRequest, stockData);
            } else {
                stockDataTrans = optionalStockTrans.get().convertToData();
                stockDataTrans.setQuantity(stockDataTrans.getQuantity() + stockUpdateRequest.getQuantity());
            }
            stockData.setQuantity(stockData.getQuantity() - stockUpdateRequest.getQuantity());
            createStockHistory(stockUpdateRequest, stockData, transactionType, currentDate, currentTime);
            createStockHistory(stockUpdateRequest, stockDataTrans, transactionType, currentDate, currentTime);
        }
        stockRepository.save(stockData.convertToEntity());
    }

    @Override
    public PageResponse<StockLocationsResponse> getStockLocations(long productId, int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Stock> pageStock = stockRepository.findStocksByProductId(productId, paging);

        List<StockLocationsResponse> products = pageStock.getContent().stream()
                .map(stock -> {
                    StockLocationsResponse stockLocationsResponse = new StockLocationsResponse();
                    Scheme location = stock.getLocation();
                    stockLocationsResponse.setId(stock.getId());
                    stockLocationsResponse.setLocation(new SimpleValue<>(location.getId(), location.getName()));
                    stockLocationsResponse.setQuantity(stock.getQuantity());
                    return stockLocationsResponse;
                })
                .collect(Collectors.toList());

        return new PageResponse<>(products, pageStock.getNumber(),
                pageStock.getTotalElements(),
                pageStock.getTotalPages());
    }

    private StockData getNewStockDataTrans(StockUpdateRequest stockUpdateRequest, StockData stockData) {
        StockData stockDataTrans;
        Optional<Scheme> optionalLocation =
                schemeRepository.findSchemesByIdAndStatusTrueAndType(stockUpdateRequest.getNewLocation(), SchemeUtils.SchemeType.LOCATION.getId());
        if (optionalLocation.isEmpty()) {
            throw new SchemeNotFoundException("Scheme not found, schemeId : " + stockUpdateRequest.getNewLocation());
        }
        stockDataTrans = new StockData();
        stockDataTrans.setProduct(stockData.getProduct());
        stockDataTrans.setLocation(optionalLocation.get().convertToData());
        stockDataTrans.setQuantity(stockUpdateRequest.getQuantity());
        stockRepository.save(stockDataTrans.convertToEntity());
        return stockDataTrans;
    }

    private void createStockHistory(StockUpdateRequest stockUpdateRequest, StockData stockData, int transactionType,
                                    LocalDate currentDate, LocalTime currentTime) {
        StockHistoryData stockHistoryData = new StockHistoryData();
        stockHistoryData.setStock(stockData);
        stockHistoryData.setLocation(stockData.getLocation());
        stockHistoryData.setTransactionType(transactionType);
        stockHistoryData.setComment(stockUpdateRequest.getComment());
        stockHistoryData.setQuantity(stockUpdateRequest.getQuantity());
        stockHistoryData.setTime(currentTime);
        stockHistoryData.setDate(currentDate);
        stockHistoryRepository.save(stockHistoryData.convertToEntity());
    }

    private StockData getStockData(long stockId) {
        Optional<Stock> optionalStock = stockRepository.findById(stockId);
        if (optionalStock.isEmpty()) {
            throw new EntityNotFoundException("Stock not found, StockId : " + stockId);
        }
        return optionalStock.get().convertToData();
    }
}
