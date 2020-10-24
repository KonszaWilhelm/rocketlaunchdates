package com.launchdatesandshop.controllers;

import com.launchdatesandshop.entities.Launch;

import com.launchdatesandshop.entities.User;
import com.launchdatesandshop.repositories.UserRepository;
import com.launchdatesandshop.service.LaunchService;
import com.launchdatesandshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class EmailController {

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    LaunchService launchService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;


    @PostMapping("**/contact")
    public String getReminders(@ModelAttribute("user") User user) {


        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("kvwork843@gmail.com");
        message.setTo(user.getEmail());
        String mailSubject = "Hey";

        StringBuilder sb = new StringBuilder();
        List<Launch> launchList = launchService.getAllLaunches();
        for (Launch l : launchList) {
            sb.append(l.toString());
        }

        String mailContent = sb.toString();
        message.setSubject(mailSubject);
        message.setText(mailContent);

        mailSender.send(message);
        return "message";
    }


    @GetMapping("/showReminderForm")
    public String showReminderForm(Model model) {
        //get users
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepository.findByEmail(currentPrincipalName);
        long id = user.getIdUser();
        model.addAttribute("id", id);

        //set User as model
        model.addAttribute("user", user);
        return "get_reminders";
    }


}
