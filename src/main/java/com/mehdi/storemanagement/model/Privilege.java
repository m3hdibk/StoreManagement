package com.mehdi.storemanagement.model;

import com.mehdi.storemanagement.model.dto.PrivilegeData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity(name = "privilege")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Privilege implements Serializable {

    @Serial
    private static final long serialVersionUID = -3973421857263205284L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String role;

    public PrivilegeData convertToData() {
        PrivilegeData privilegeData = new PrivilegeData();
        privilegeData.setId(id);
        privilegeData.setName(name);
        privilegeData.setRole(role);
        return privilegeData;
    }
}
