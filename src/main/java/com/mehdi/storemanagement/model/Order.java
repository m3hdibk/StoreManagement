package com.mehdi.storemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order implements Serializable {


    @Serial
    private static final long serialVersionUID = -5605831918801069194L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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


}
