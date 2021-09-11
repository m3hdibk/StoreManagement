package com.mehdi.storemanagement.model.dto;

import com.mehdi.storemanagement.model.Client;
import com.mehdi.storemanagement.model.Order;
import com.mehdi.storemanagement.model.ProductOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderData implements Serializable {


    @Serial
    private static final long serialVersionUID = 1875431867658485384L;

    private long id;
    private String ref;

    private ClientData client;

    private ProductOrderData productOrder;

    private double amount;

    private double discount;

    private double totalAmount;
    private int paymentType;
    private boolean status;

    public Order convertToEntity(){
        Order order = new Order();
        order.setId(id);
        order.setRef(ref);
        order.setClient(client.convertToEntity());
        order.setAmount(amount);
        order.setDiscount(discount);
        order.setTotalAmount(amount);
        order.setPaymentType(paymentType);
        order.setStatus(status);
        return order;
    }
}
