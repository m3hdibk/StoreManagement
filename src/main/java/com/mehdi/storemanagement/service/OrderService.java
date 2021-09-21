package com.mehdi.storemanagement.service;

import com.mehdi.storemanagement.model.dto.OrderData;
import com.mehdi.storemanagement.model.dto.request.OrderRequest;
import com.mehdi.storemanagement.model.dto.response.PageResponse;

public interface OrderService {

    void createOrder(OrderRequest orderRequest);
    PageResponse<OrderData> getAllOrders(int page, int size);

}
