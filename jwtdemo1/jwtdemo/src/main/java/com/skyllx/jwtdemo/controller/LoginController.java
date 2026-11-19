package com.skyllx.jwtdemo.controller;

import com.skyllx.jwtdemo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(
            @RequestParam String username,
            @RequestParam String password,
            Model model
    ) {
        try {
            String token = loginService.authenticateAndGenerateToken(username, password);
            if (token != null) {
                model.addAttribute("token", token);
                return "token";
            } else {
                model.addAttribute("error", "Invalid Credentials");
                return "login";
            }
        } catch (AuthenticationException e){
            model.addAttribute("error", "Invalid Credentials");
        }
        return  "/login";
    }
}