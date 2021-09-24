package com.mehdi.storemanagement.util;

import com.mehdi.storemanagement.model.dto.ClientData;
import com.mehdi.storemanagement.model.dto.OrderData;
import com.mehdi.storemanagement.model.dto.SimpleValue;
import com.mehdi.storemanagement.model.dto.response.OrderResponse;

import java.util.List;
import java.util.stream.Collectors;

public final class ResponseConverterUtils {

    public static List<OrderResponse> convertToOrderResponse(List<OrderData> orderDataList) {
        return orderDataList.stream().map(orderData -> {
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setId(orderData.getId());
            orderResponse.setRef(orderData.getRef());
            ClientData clientData = orderData.getClient();
            orderResponse.setClient(new SimpleValue<>(clientData.getId(),
                    clientData.getLastName() != null ? clientData.getName() + " " + clientData.getLastName() :
                            clientData.getName()));
            orderResponse.setStatus(orderData.isStatus());
            orderResponse.setTotal(orderData.getTotalAmount());
            StockUtils.PaymentType paymentType = StockUtils.PaymentType.getById(orderData.getPaymentType());
            orderResponse.setPaymentType(new SimpleValue<>(paymentType.getId(), paymentType.getName()));
            return orderResponse;
        }).collect(Collectors.toList());
    }
}
