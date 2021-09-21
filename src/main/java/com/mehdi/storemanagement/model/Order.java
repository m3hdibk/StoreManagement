package com.mehdi.storemanagement.model;

import com.mehdi.storemanagement.model.dto.OrderData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order implements Serializable {


    @Serial
    private static final long serialVersionUID = -5605831918801069194L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String ref;

    @ManyToOne
    private Client client;

    @OneToMany
    private List<ProductOrder> productOrder;

    private double amount;

    private double discount;

    private double totalAmount;
    private int paymentType;
    private boolean status;

    public OrderData convertToData() {
        OrderData orderData = new OrderData();

        orderData.setId(id);
        orderData.setRef(ref);
        orderData.setClient(client.convertToData());
        orderData.setProductOrder(productOrder.stream().map(ProductOrder::convertToData).collect(Collectors.toList()));
        orderData.setAmount(amount);
        orderData.setDiscount(discount);
        orderData.setTotalAmount(totalAmount);
        orderData.setPaymentType(paymentType);
        orderData.setStatus(status);
        return orderData;
    }
}
