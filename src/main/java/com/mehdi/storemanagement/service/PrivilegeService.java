package com.mehdi.storemanagement.service;

import com.mehdi.storemanagement.model.dto.PrivilegeData;

import java.util.List;
import java.util.Set;

public interface PrivilegeService {

    void updatePrivilege(PrivilegeData privilegeData);
    List<PrivilegeData> getPrivilegesByRole(String role);
    List<PrivilegeData> getPrivilegesByRoles(Set<String> roles);
}
