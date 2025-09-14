package com.example.thymeleaf2_demo;  // use your actual package name

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Hello, Thymeleaf + Spring Boot!");
        return "index"; // this looks for index.html inside templates/
    }

    @GetMapping("/login")
    public String login(Model model) {
    model.addAttribute("pageTitle", "Login");
    
    return "login"; 
}
}
