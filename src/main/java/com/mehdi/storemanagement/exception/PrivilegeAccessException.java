package com.mehdi.storemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "You don't have the privilege")
public class PrivilegeAccessException extends RuntimeException {


    public PrivilegeAccessException(String exception) {
        super("You don't have the privilege : " + exception);
    }

}
