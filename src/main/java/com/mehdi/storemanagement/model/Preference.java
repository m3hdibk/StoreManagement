package com.mehdi.storemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;

@Entity(name = "preference")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Preference implements Serializable {

    @Serial
    private static final long serialVersionUID = -8281353877948527774L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    
}
