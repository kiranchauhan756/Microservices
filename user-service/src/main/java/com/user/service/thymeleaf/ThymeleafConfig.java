package com.user.service.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ThymeleafConfig {

    @GetMapping("/show-form")
    public String showForm(){
        return "hello_world";
    }

    @GetMapping("/process-form")
    public String processForm(@RequestParam("studentName") String studentName, Model model){
        model.addAttribute("studentName",studentName);
        return "process_form";
    }
}
