package com.berachad.patientsorm;

import com.berachad.patientsorm.Entity.Patient;
import com.berachad.patientsorm.Repository.patientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class PatientsOrmApplication implements CommandLineRunner {
    @Autowired
    private com.berachad.patientsorm.Repository.patientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(PatientsOrmApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        patientRepository.save(new Patient(null,"mohamed",new Date(),false));
        patientRepository.save(new Patient(null,"Bader",new Date(),true));
        patientRepository.save(new Patient(null,"Ayoub",new Date(),true));

        List<Patient> patients =patientRepository.findAll();
        patients.forEach((patient)->{
            System.out.println("-----------------");
            System.out.println(patient.getId());
            System.out.println(patient.getNom());
            System.out.println(patient.getDateNaissance());
            System.out.println(patient.isMalade());
            System.out.println("==================");


        });

    }
}
