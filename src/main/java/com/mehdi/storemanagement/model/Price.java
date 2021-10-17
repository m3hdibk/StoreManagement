package com.mehdi.storemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity(name = "price")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Price implements Serializable {

    @Serial
    private static final long serialVersionUID = -3979421857263205284L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double amount;
    private int method;
    private int rule;
}
