package com.mehdi.storemanagement.model;

import com.mehdi.storemanagement.model.dto.UserData;
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

@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User implements Serializable {


    @Serial

    private static final long serialVersionUID = -2289370929564118952L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int type;
    private String name;
    private String email;
    private String address;
    private String lastName;
    private int phoneNumber;

    public UserData convertToData() {
        UserData userData = new UserData();
        userData.setId(id);
        userData.setName(name);
        userData.setLastName(lastName);
        userData.setPhoneNumber(phoneNumber);
        userData.setEmail(email);
        userData.setAddress(address);
        userData.setType(type);
        return userData;
    }

}
