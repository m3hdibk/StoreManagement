package com.mehdi.storemanagement.service;

import com.mehdi.storemanagement.model.dto.request.StockUpdateRequest;
import com.mehdi.storemanagement.model.dto.response.PageResponse;
import com.mehdi.storemanagement.model.dto.response.StockLocationsResponse;
import com.mehdi.storemanagement.model.dto.response.StockSumQuantityResponse;

public interface StockService {

    PageResponse<StockSumQuantityResponse> getAllStockInformation(int page, int size);

    PageResponse<StockSumQuantityResponse> searchStockByProduct(boolean useDefaultLocation,
                                                                String searchInput, int page, int size);


    void updateStock(StockUpdateRequest stockUpdateRequest, long stockId);

    PageResponse<StockLocationsResponse> getStockLocations(long productId, int page, int size);

}