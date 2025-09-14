package com.example.thymeleaf2_demo;  // use your actual package name

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {

    @GetMapping("/login")
    public String login(Model model) {
    model.addAttribute("pageTitle", "Login");
    
    return "login"; 
    }
    @GetMapping("/ngo")
    public String ngo_listing(Model model) {
        model.addAttribute("pageTitle", "NGO DIRECTORY");

        return "ngo";
    }
    @GetMapping("/donation")
    public String donation(Model model) {
        model.addAttribute("pageTitle", "DONATION FORM");

        return "donation";
    }
    

}
