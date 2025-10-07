package com.mvc.security.springSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping("/showMyLoginPage")
    public String getHome(){
        return "fancy-login";
    }
}
