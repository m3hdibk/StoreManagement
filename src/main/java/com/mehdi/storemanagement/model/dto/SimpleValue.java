package com.mehdi.storemanagement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SimpleValue<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 684194365238388591L;

    private long id;
    private T value;
}
