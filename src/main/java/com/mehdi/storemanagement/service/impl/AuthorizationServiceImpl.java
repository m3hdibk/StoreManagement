package com.mehdi.storemanagement.service.impl;

import com.mehdi.storemanagement.exception.PrivilegeAccessException;
import com.mehdi.storemanagement.model.dto.PrivilegeData;
import com.mehdi.storemanagement.service.AuthorizationService;
import com.mehdi.storemanagement.service.PrivilegeService;
import com.mehdi.storemanagement.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mehdi.storemanagement.util.PrivilegeConstants.ADMIN_ROLE;

@Service
@Slf4j
public class AuthorizationServiceImpl implements AuthorizationService {

    private final PrivilegeService privilegeService;
    private final HashMap<String, List<PrivilegeData>> privilegesByRole;
    private LocalDateTime time;

    public AuthorizationServiceImpl(PrivilegeService privilegeService) {
        this.privilegeService = privilegeService;
        this.privilegesByRole = new HashMap<>();
    }


    public boolean can(String expectedPrivilege) {
        Set<String> userRoles = SecurityUtils.getUserRoles();
        if (userRoles.contains(ADMIN_ROLE)) {
            return true;
        }
        LocalDateTime refreshDate = LocalDateTime.now().minusMinutes(15);
        boolean checkSavedData = time != null;
        if (time != null && time.isBefore(refreshDate)) {
            privilegesByRole.clear();
            time = null;
            checkSavedData = false;
            log.debug("Clear privileges map");
        }
        if (checkSavedData && hasPrivilege(privilegesByRole, userRoles, expectedPrivilege, false)) {
            return true;
        }
        List<PrivilegeData> privileges = privilegeService.getPrivilegesByRoles(userRoles);
        Map<String, List<PrivilegeData>> value = privileges.stream()
                .collect(Collectors.groupingBy(PrivilegeData::getRole));
        return hasPrivilege(value, userRoles, expectedPrivilege, true);

    }

    @Override
    public void checkPrivilege(String expectedPrivilege) throws PrivilegeAccessException {
        if (!can(expectedPrivilege)) {
            throw new PrivilegeAccessException(expectedPrivilege);
        }
    }

    private boolean hasPrivilege(Map<String, List<PrivilegeData>> privilegesMap, Set<String> userRoles,
                                 String expectedPrivilege, boolean updateMap) {
        if (time == null) {
            time = LocalDateTime.now();
        }
        for (String role : userRoles) {
            List<PrivilegeData> privileges = privilegesMap.get(role);
            if (privileges != null) {
                if (updateMap) {
                    privilegesByRole.put(role, privileges);
                }
                for (PrivilegeData privilegeData : privileges) {
                    if (privilegeData.getName().equals(expectedPrivilege)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
