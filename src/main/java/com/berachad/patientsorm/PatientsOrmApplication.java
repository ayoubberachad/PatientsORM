package com.berachad.patientsorm;

import com.berachad.patientsorm.Entity.Patient;
import com.berachad.patientsorm.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class PatientsOrmApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(PatientsOrmApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i=0;i<100;i++) {
            patientRepository.save(new Patient(null, "mohamed"+i, new Date(), false));
        }
        Page<Patient> patients =patientRepository.findAll(PageRequest.of(4 ,5));
        System.out.println("Total page : "+patients.getTotalPages());
        System.out.println("Total elements : "+patients.getTotalElements());
        System.out.println("Num Page : "+patients.getNumber());


        patients.forEach((patient)->{
        System.out.println("-----------------");
        System.out.println(patient.getId());
        System.out.println(patient.getNom());
        System.out.println(patient.getDateNaissance());
        System.out.println(patient.isMalade());
    });

        System.out.println("==================");

        //patientRepository.findById(1L).orElseThrow(()->new RuntimeException("Patient not found "));
        Patient patient =patientRepository.findById(1L).orElse(null);
        if (patient!=null){
            System.out.println(patient.getNom());
            System.out.println(patient.getId());
            System.out.println(patient.isMalade());
        }
        patient.setNom("New name ");
        patient.setMalade(true);
        patientRepository.save(patient);
       // patientRepository.deleteById(1l);
        System.out.println("===========CCCCCCCCCC=======");


     List<Patient>  patients1 =patientRepository.findByMalade(true);
        patients1.forEach((Patient) -> {
            System.out.println(Patient.getNom());
        });
    }

}
