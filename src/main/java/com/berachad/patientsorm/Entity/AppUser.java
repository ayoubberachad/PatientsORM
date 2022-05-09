package com.berachad.patientsorm.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    private String userId;
    private String name;
    private String password;
    private boolean active;
    private List<AppRole> appRoles= new ArrayList<>();


}
