package com.example.thymeleaf2_demo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {
    @GetMapping("/home")
    public String home_page(Model model){
        model.addAttribute("pageTitle" , "HOME PAGE");
        return "home";
    }
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
    
    @GetMapping("/story")
    public String story_of_impact(Model model) {
        model.addAttribute("pageTitle", "STORIES OF IMPACT");
        return "story_of_impact";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
    model.addAttribute("pageTitle", "Sign Up");
    return "signup"; 
    }

    @GetMapping("/ngo_profile")
    public String ngo_profile(Model model){
        model.addAttribute("pageTitle" , "NGO DASHBOARD");
        return "ngo_dashboard";
    }
}
