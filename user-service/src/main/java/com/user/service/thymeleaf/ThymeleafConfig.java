package com.user.service.thymeleaf;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ThymeleafConfig {

    @GetMapping("/show-form")
    public String showForm(){
        return "hello_world";
    }

    @PostMapping("/process-form")
    public String processForm(){
        return "process_form";
    }

//    @GetMapping("/process-form-v2")
//    public String processFormV2(HttpServletRequest httpServletRequest,Model model){
//        String name=httpServletRequest.getParameter("studentName");
//        name=name.toUpperCase();
//        String result="Yo "+name;
//        model.addAttribute("message",result);
//        return "process_form_v2";
//    }

    @PostMapping("/process-form-v2")
    public String processFormV3(@RequestParam("studentName") String studentName,Model model){
        studentName=studentName.toUpperCase();
        String result="Yo I am from v3 "+studentName;
        model.addAttribute("message",result);
        return "process_form_v2";
    }
}
