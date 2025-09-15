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

    @GetMapping("/donor_history")
    public String donor_history(Model model) {
    model.addAttribute("pageTitle", "Donor History");
    return "donor_history"; 
    }
    @GetMapping("/ngo_history")
    public String ngo_history(Model model) {
    model.addAttribute("pageTitle", "NGO History");
    return "ngo_history"; 
    }
     @GetMapping("/volunteer_history")
    public String volunteer_history(Model model) {
        model.addAttribute("pageTitle", "HISTORY OF VOLUNTEER");
        return "history_volunteer";
    }
     @GetMapping("/pickup_history")
    public String pickup_agent_history(Model model) {
        model.addAttribute("pageTitle", "HISTORY OF PICKUP AGENT");
        return "pickup_agent_history";
    }
    @GetMapping("/post_story")
    public String post_story(Model model) {
        model.addAttribute("pageTitle", "POST IMPACT STORIES");
        return "post_story";
    }
    @GetMapping("/donor_dashboard")
    public String donor_dashboard(Model model) {
    model.addAttribute("pageTitle", "Donor Dashboard");
    return "donor_dashboard"; 
    }
}
