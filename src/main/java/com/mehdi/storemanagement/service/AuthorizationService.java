package com.mehdi.storemanagement.service;

import com.mehdi.storemanagement.exception.PrivilegeAccessException;

public interface AuthorizationService {

    boolean can(String privilege);

    void checkPrivilege(String expectedPrivilege) throws PrivilegeAccessException;
}
