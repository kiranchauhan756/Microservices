package com.example.demo.controller;

import com.example.demo.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ThymeleafConfig {

    @GetMapping("/show_form")
    public String studentForm(Model model){
        Student theStudent=new Student();
    model.addAttribute("student",theStudent);
     return "show_form";
    }

    @PostMapping("/process_form")
    public String processForm(@ModelAttribute("student") Student student){
        System.out.println("student info "+student.getFirstName()+" "+student.getLastName()+" "+student.getCountry());
        return "confirmation_form";
    }
}
