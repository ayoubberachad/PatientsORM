package com.berachad.patientsorm.security.sec.service;

import com.berachad.patientsorm.security.sec.entity.AppRole;
import com.berachad.patientsorm.security.sec.entity.AppUser;
import com.berachad.patientsorm.security.sec.repository.AppRoleRepository;
import com.berachad.patientsorm.security.sec.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Service
@Slf4j
@Transactional
public class SecurityServiceImpl implements SecurityService {

    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
@Autowired
    private PasswordEncoder passwordEncoder;

    // Injection dependency used constructor
    // WE can use @autowaired annotation for injection de....
    // We can use anotations @AllArgsConstructor and delete this one
    public SecurityServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
    }

    @Override
    public AppUser saveNewUser(String username, String password, String rePassword) {
        // TEST equality Password
        if (!password.equals(rePassword)) throw new RuntimeException("Password not match !!!");
        // Encoder password
        String hashedPWD = passwordEncoder.encode(password);
        AppUser appUser = new AppUser();
        // Generate new ID String
        appUser.setUserId(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setPassword(hashedPWD);
        AppUser savedAppUser = appUserRepository.save(appUser);
        // Save user
        return savedAppUser;
    }

    @Override
    public AppRole saveNewRole(String roleName, String description) {
        AppRole appRole =appRoleRepository.findByRoleName(roleName);
        if (appRole!=null) throw new RuntimeException("Role "+ roleName +" already exist ");
        appRole = new AppRole();
        appRole.setRoleName(roleName);
        appRole.setDescription(description);
        AppRole savedAppRole = appRoleRepository.save(appRole);
        return savedAppRole;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser==null) throw new RuntimeException("User "+ username +" Not Found ");
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if (appUser==null) throw new RuntimeException("User "+ username +" Not Found ");
        appUser.getAppRoles().add(appRole);
    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser==null) throw new RuntimeException("User "+ username +" Not Found ");
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if (appUser==null) throw new RuntimeException("User "+ username +" Not Found ");
        appUser.getAppRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUserName(String username) {
        return appUserRepository.findByUsername(username);
    }
}
