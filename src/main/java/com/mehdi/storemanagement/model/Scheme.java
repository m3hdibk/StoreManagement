package com.mehdi.storemanagement.model;


import com.mehdi.storemanagement.model.dto.SchemeData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity(name = "scheme")
@Table(uniqueConstraints = { @UniqueConstraint(name = "UniqueTypeAndName",
        columnNames = { "type", "name" }) })
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Scheme implements Serializable {


    @Serial
    private static final long serialVersionUID = -5571335603815079328L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "type", nullable = false)
    private int type;
    @Column(name = "name", nullable = false)
    private String name;

    private String description;

    @Column(name = "status", nullable = false)
    private boolean status;

    public SchemeData convertToData() {
        SchemeData schemeData = new SchemeData();
        schemeData.setId(id);
        schemeData.setType(type);
        schemeData.setName(name);
        schemeData.setDescription(description);
        schemeData.setStatus(status);
        return schemeData;
    }


}
