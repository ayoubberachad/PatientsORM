package com.berachad.patientsorm.web;

import com.berachad.patientsorm.Repository.patientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class patientController {
     private patientRepository patientRepository;
    @GetMapping(path="/index")
     public  String patients(){


        // return page (Vue)
         return "patients";
     }
}
