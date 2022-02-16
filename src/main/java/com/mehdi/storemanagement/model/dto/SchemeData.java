package com.mehdi.storemanagement.model.dto;

import com.mehdi.storemanagement.model.Scheme;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class SchemeData implements Serializable {

    @Serial
    private static final long serialVersionUID = -939319897719002986L;

    private long id;


    private int type;

    @NotBlank(message = "name must have a value")
    @NotNull(message = "Name cannot be null")
    private String name;
    private String description;
    private boolean status;
    private boolean defaultItem;



    public Scheme convertToEntity() {
        Scheme scheme = new Scheme();
        scheme.setId(id);
        scheme.setType(type);
        scheme.setName(name);
        scheme.setDescription(description);
        scheme.setStatus(status);
        scheme.setDefaultItem(defaultItem);
        return scheme;
    }
}
