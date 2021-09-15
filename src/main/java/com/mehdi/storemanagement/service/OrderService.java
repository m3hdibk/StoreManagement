package com.mehdi.storemanagement.service;

import com.mehdi.storemanagement.model.dto.request.OrderRequest;

public interface OrderService {

    void createOrder(OrderRequest orderRequest);
}
