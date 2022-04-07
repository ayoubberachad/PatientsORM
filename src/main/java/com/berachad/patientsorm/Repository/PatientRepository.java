package com.berachad.patientsorm.Repository;


import com.berachad.patientsorm.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    public List<Patient> findByMalade(boolean m);

}
