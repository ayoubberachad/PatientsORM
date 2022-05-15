package com.berachad.patientsorm.security.sec.service;

import com.berachad.patientsorm.security.sec.entity.AppRole;
import com.berachad.patientsorm.security.sec.entity.AppUser;
import org.springframework.scheduling.support.SimpleTriggerContext;

public interface SecurityService {
    AppUser saveNewUser(String username ,String password, String rePassword);
    AppRole saveNewRole(String roleName, String description);
    void addRoleToUser(String username, String roleName);
    void removeRoleFromUser(String username, String roleName);
    AppUser loadUserByUserName(String username);
}
