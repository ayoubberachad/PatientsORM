package com.berachad.patientsorm.security.sec.repository;

import com.berachad.patientsorm.security.sec.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String> {
 AppUser findByUsername(String username);
}
