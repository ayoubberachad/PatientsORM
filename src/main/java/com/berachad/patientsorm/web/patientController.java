package com.berachad.patientsorm.web;

import com.berachad.patientsorm.Entity.Patient;
import com.berachad.patientsorm.Repository.patientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class patientController {
     private patientRepository patientRepository;
    @GetMapping("/index")
    // get  all patinets
     public  String patients(Model model,
                             @RequestParam(name="page", defaultValue = "0") int page,
                             @RequestParam(name = "size", defaultValue = "5") int size,
                             @RequestParam(name = "keyword", defaultValue = "") String keyword
        ){
        Page<Patient> pagePatients = patientRepository.findByNomContains(keyword,PageRequest.of(page,size));
        model.addAttribute("listPatients",pagePatients.getContent());
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("currentKeyword",keyword);

        // return page (Vue)
         return "patient";
     }
    @GetMapping("/delete")
     public String delete(Long id,String keyword,int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
     }
    @GetMapping("/")
    public String home(){
         return "redirect:/index";
    }
//Format JSON to use ANGULAR
    @GetMapping("/patients")
    @ResponseBody
    public List<Patient> listPatients(){
            return patientRepository.findAll();
    }
@GetMapping("/formPatients")
    public String formPatients(Model model){
        model.addAttribute("patient", new Patient());
        return "formPatients";
}
//Validation . anotations
@PostMapping(path = "/save")
public String save(Model model , @Valid Patient patient, BindingResult bindingResult,
                   @RequestParam(defaultValue = "0") int page,
                   @RequestParam(defaultValue = "") String keyword){
        if (bindingResult.hasErrors()) return "formPatients";
        patientRepository.save(patient);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    };

    @GetMapping("/editPatient")
    public String editPatient(Model model, Long id,String keyword,int page){
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient ==null) throw new RuntimeException("patient not fond ");
        model.addAttribute("patient",patient);
        model.addAttribute("page",page);
        model.addAttribute("currentKeyword",keyword);
        return "editPatients";
    }

}
