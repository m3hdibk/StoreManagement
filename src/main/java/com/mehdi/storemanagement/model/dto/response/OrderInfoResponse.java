package com.mehdi.storemanagement.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderInfoResponse extends OrderResponse {

    @Serial
    private static final long serialVersionUID = -7822160051790655597L;

    private List<ProductOrderResponse> products;

    public OrderInfoResponse(OrderResponse orderResponse) {
        super(orderResponse);
    }

}
