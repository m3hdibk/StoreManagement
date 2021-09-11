package com.mehdi.storemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Scheme")
public class SchemeNotFoundException extends RuntimeException {


    public SchemeNotFoundException(String exception) {
        super(exception);
    }

}
