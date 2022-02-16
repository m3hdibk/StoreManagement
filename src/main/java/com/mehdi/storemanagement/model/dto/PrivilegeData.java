package com.mehdi.storemanagement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PrivilegeData implements Serializable {

    @Serial
    private static final long serialVersionUID = 4294403988539474025L;

    private long id;
    private String name;
    private String role;


}
