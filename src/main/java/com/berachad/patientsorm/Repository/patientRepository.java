package com.berachad.patientsorm.Repository;


import com.berachad.patientsorm.Entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface patientRepository extends JpaRepository<Patient,Long> {

   // public List<Patient> findByMalade(boolean m);
    Page<Patient> findByNomContains(String kw, Pageable pageable);

}
