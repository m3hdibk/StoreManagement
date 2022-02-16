package com.mehdi.storemanagement.repository;

import com.mehdi.storemanagement.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    List<Privilege> findByRoleIn(Set<String> roles);

}
