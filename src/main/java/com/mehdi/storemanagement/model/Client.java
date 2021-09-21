package com.mehdi.storemanagement.model;

import com.mehdi.storemanagement.model.dto.ClientData;
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

@Entity(name = "client")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client implements Serializable {


    @Serial

    private static final long serialVersionUID = -2289370929564118952L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String lastName;
    private int phoneNumber;

    public ClientData convertToData() {
        ClientData clientData = new ClientData();
        clientData.setId(id);
        clientData.setName(name);
        clientData.setLastName(lastName);
        clientData.setPhoneNumber(phoneNumber);
        return clientData;
    }

}
