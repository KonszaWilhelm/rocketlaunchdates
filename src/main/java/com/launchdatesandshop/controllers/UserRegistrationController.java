package com.launchdatesandshop.controllers;

import com.launchdatesandshop.dto.UserRegistrationDto;

import com.launchdatesandshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    //DI
    private UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    //returning empty user object for FORM registration.html
    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }


    //method handler to return reg.html
    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    //Post request method handler
    @PostMapping
    //passing user object and binding it to dto obj
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        userService.saveUser(registrationDto);
        return "redirect:/registration?success";
        /*retrieved in registration.html param success*/
    }

}
