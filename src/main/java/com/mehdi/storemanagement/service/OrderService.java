package com.mehdi.storemanagement.service;

import com.mehdi.storemanagement.model.dto.request.OrderRequest;
import com.mehdi.storemanagement.model.dto.response.OrderInfoResponse;
import com.mehdi.storemanagement.model.dto.response.OrderResponse;
import com.mehdi.storemanagement.model.dto.response.PageResponse;

public interface OrderService {

    void createOrder(OrderRequest orderRequest);
    PageResponse<OrderResponse> getAllOrders(int page, int size, String sort);
    OrderInfoResponse getOrderInfo(long id);

}
