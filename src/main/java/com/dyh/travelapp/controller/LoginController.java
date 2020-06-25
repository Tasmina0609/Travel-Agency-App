package com.dyh.travelapp.controller;

import com.dyh.travelapp.model.User;
import com.dyh.travelapp.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService=userService;
    }

    @GetMapping("/home")
    public String getHome() {
        return "home";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/welcome")
    public String getWelcome(Model model) {
        return "index";
    }

    @GetMapping("/register")
    public String registerUSer(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Errors errors, Model model) {
        if(userService.checkUniqueUsername(user.getUsername())){
            model.addAttribute("exist",true );
            return "register";
        }
        else if (errors.hasErrors()) {
            return "register";
        }
        else {
            userService.saveUser(user);
            model.addAttribute("registerOK", "Registration OK");
            return "login";
        }
    }

}
