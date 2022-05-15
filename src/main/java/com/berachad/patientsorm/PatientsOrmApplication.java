package com.berachad.patientsorm;

import com.berachad.patientsorm.security.sec.entity.AppUser;
import com.berachad.patientsorm.security.sec.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PatientsOrmApplication implements CommandLineRunner {
    @Autowired
    private com.berachad.patientsorm.Repository.patientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(PatientsOrmApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        //   patientRepository.save(new Patient(null,"mohamed",new Date(),false));
        // patientRepository.save(new Patient(null,"Bader",new Date(),true));
        //   patientRepository.save(new Patient(null,"Ayoub",new Date(),true));

//        List<Patient> patients =patientRepository.findAll();
//        patients.forEach((patient)->{
//            System.out.println("-----------------");
//            System.out.println(patient.getId());
//            System.out.println(patient.getNom());
//            System.out.println(patient.getDateNaissance());
//            System.out.println(patient.isMalade());
//            System.out.println("==================");
//
//
//        });

    }

    @Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("Moahmmed1","ayoub" , "ayoub");
            securityService.saveNewUser("Ayoub3" , "555","555");
            securityService.saveNewRole("User","");
            securityService.saveNewRole("ADMIN","");

            securityService.addRoleToUser("Moahmmed1","ADMIN");
            securityService.addRoleToUser("Ayoub3","User");

        };
    }

}
