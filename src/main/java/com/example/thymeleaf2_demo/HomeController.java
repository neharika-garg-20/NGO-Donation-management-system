package com.example.thymeleaf2_demo;

import com.example.thymeleaf2_demo.model.*;
import com.example.thymeleaf2_demo.repository.DonorRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.thymeleaf2_demo.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    
@GetMapping("/signup")
    public String signup(Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }
        model.addAttribute("pageTitle", "Sign Up");
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignup(@ModelAttribute User user, BindingResult result, RedirectAttributes redirectAttributes) {

      
        org.springframework.validation.Validator validator = new org.springframework.validation.beanvalidation.LocalValidatorFactoryBean();
        ((org.springframework.validation.beanvalidation.LocalValidatorFactoryBean) validator).afterPropertiesSet();
        org.springframework.validation.DataBinder binder = new org.springframework.validation.DataBinder(user);
        BindingResult validationResult = binder.getBindingResult();

        if (validationResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", validationResult);
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("error", "Please correct the errors in the form");
            return "redirect:/signup";
        }

        try {
            userService.registerUser(user);
            redirectAttributes.addFlashAttribute("message", user.getUserType() + " registered successfully!");
            // logger.info("User registered successfully: {}", user.getUsername());
            return "redirect:/login";
        } catch (DuplicateKeyException e) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
            redirectAttributes.addFlashAttribute("user", user);
            String errorMessage = e.getCause() != null && e.getCause().getMessage().contains("email") ?
                    "This email is already registered. Please use a different email or log in." :
                    "This username is already taken. Please choose another.";
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/signup";
         } catch (Exception e) {
            // logger.error("Registration error: {}", e.getMessage(), e);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", validationResult);
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/signup";
        }
    }

    @GetMapping("/home")
    public String home_page(Model model) {
        model.addAttribute("pageTitle", "HOME PAGE");
        return "home";
    }

  @GetMapping("/login")
public String login(Model model) {
    model.addAttribute("pageTitle", "Login");
    return "login";
}

@PostMapping("/login")
public String processLogin(@ModelAttribute User user, Model model) {

    boolean success = userService.login(user);
    System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxUserType: " + user.getUserType());

 String userType = user.getUserType().toLowerCase();
    switch (userType) {
        case "donor":
            if (success) return "redirect:/donor_dashboard";
            break;
        case "ngo":
            if (success) return "redirect:/ngo_profile";
            break;
        case "pickup_agent":
            if (success) return "redirect:/pickup_dashboard";
            break;
        case "volunteer":
            if (success) return "redirect:/volunteer_history";
            break;
        default:
            model.addAttribute("error", "Please select a valid user type!");
            return "login";
    }

    model.addAttribute("error", "Invalid email or password!");
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


    @GetMapping("/ngo_profile")
    public String ngo_profile(Model model) {
        model.addAttribute("pageTitle", "NGO DASHBOARD");
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

    @GetMapping("/pickup_dashboard")
    public String pickup_dashboard(Model model) {
        model.addAttribute("pageTitle", "Pickup Dashboard");
        return "pickup_agent_dashboard";
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

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("pageTitle", "ADMIN");
        return "admin";
    }

   
}