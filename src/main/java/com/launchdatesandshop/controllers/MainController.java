package com.launchdatesandshop.controllers;

import com.launchdatesandshop.entities.User;
import com.launchdatesandshop.repositories.UserRepository;
import com.launchdatesandshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {



    @GetMapping("/login")
    public String login() {
        return "login"; //own login thymeleaf template
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }
}
