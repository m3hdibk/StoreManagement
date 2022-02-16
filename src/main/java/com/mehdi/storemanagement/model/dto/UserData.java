package com.mehdi.storemanagement.model.dto;

import com.mehdi.storemanagement.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserData implements Serializable {


    @Serial
    private static final long serialVersionUID = -5994819808612014503L;

    private long id;
    private String name;
    private String lastName;
    private String email;
    private String address;
    private int phoneNumber;
    private int type;

    public User convertToEntity() {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setLastName(lastName);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        user.setAddress(address);
        user.setType(type);
        return user;
    }
}
