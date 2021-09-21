package com.mehdi.storemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not Enough Quantity")
public class NoEnoughQuantityException extends RuntimeException {


    public NoEnoughQuantityException(String exception) {
        super(exception);
    }

}
