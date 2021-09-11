package com.mehdi.storemanagement.model.dto;

import com.mehdi.storemanagement.model.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientData implements Serializable {


    @Serial
    private static final long serialVersionUID = -5994819808612014503L;

    private long id;
    private String name;
    private String lastName;
    private int phoneNumber;

    public Client convertToEntity() {
        Client client = new Client();
        client.setId(id);
        client.setName(name);
        client.setLastName(lastName);
        client.setPhoneNumber(phoneNumber);
        return client;
    }
}
