package com.skyllx.jwtdemo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null & auth.isAuthenticated()){
            String username =  auth.getName();
            model.addAttribute("username", username);
            model.addAttribute("success", "Successfully authenticated by JWT token");
        }
        return "home";
    }
}