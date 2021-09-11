package com.mehdi.storemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity(name = "productOrder")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductOrder implements Serializable {


    @Serial
    private static final long serialVersionUID = 5697940514025383003L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Product product;

    private int quantity;

}
