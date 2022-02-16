package com.mehdi.storemanagement.service.impl;

import com.mehdi.storemanagement.model.Privilege;
import com.mehdi.storemanagement.model.dto.PrivilegeData;
import com.mehdi.storemanagement.repository.PrivilegeRepository;
import com.mehdi.storemanagement.service.PrivilegeService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public record PrivilegeServiceImpl(PrivilegeRepository privilegeRepo) implements PrivilegeService {

    //TODO finish this

    @Override
    public void updatePrivilege(PrivilegeData privilegeData) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        KeycloakPrincipal principal = (KeycloakPrincipal) auth.getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        AccessToken accessToken = session.getToken();
        AccessToken.Access username = accessToken.getRealmAccess();

    }

    @Override
    public List<PrivilegeData> getPrivilegesByRole(String role) {
        return null;
    }

    @Override
    public List<PrivilegeData> getPrivilegesByRoles(Set<String> roles) {
        List<Privilege> privilegeList =  privilegeRepo.findByRoleIn(roles);
        return privilegeList.stream().map(Privilege::convertToData).collect(Collectors.toList());
    }




}
