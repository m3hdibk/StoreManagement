package com.mehdi.storemanagement.model.dto;

import com.mehdi.storemanagement.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderData implements Serializable {


    @Serial
    private static final long serialVersionUID = 1875431867658485384L;

    private long id;
    private String ref;

    private ClientData client;

    private List<ProductOrderData> productOrder;

    private double amount;

    private double discount;

    private double totalAmount;
    private int paymentType;
    private boolean status;
    private LocalDateTime date;


    public Order convertToEntity(){
        Order order = new Order();
        order.setId(id);
        order.setRef(ref);
        order.setClient(client.convertToEntity());
        order.setProductOrder(productOrder.stream().map(ProductOrderData::convertToEntity).collect(Collectors.toList()));
        order.setAmount(amount);
        order.setDiscount(discount);
        order.setTotalAmount(totalAmount);
        order.setPaymentType(paymentType);
        order.setStatus(status);
        order.setDate(date);
        return order;
    }
}
