package com.berachad.patientsorm.security.sec.repository;

import com.berachad.patientsorm.security.sec.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
 AppRole findByRoleName(String roleName);
}
