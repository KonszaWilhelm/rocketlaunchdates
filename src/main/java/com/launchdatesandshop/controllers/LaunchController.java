package com.launchdatesandshop.controllers;

import com.launchdatesandshop.entities.Launch;
import com.launchdatesandshop.entities.User;
import com.launchdatesandshop.repositories.UserRepository;
import com.launchdatesandshop.service.LaunchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LaunchController {

    @Autowired
    private LaunchService launchService;



    //display list of launches
    @GetMapping("/launches")
    public String viewHomePage(Model model) {
        model.addAttribute("listLaunches", launchService.getAllLaunches());


        return "launches";
    }

    @GetMapping("/showNewLaunchForm")
    public String showNewLaunchForm(Model model) {
        Launch launch = new Launch();
        model.addAttribute("launch", launch);
        return "new_launch";
    }

    //method handler which handles saving the launches from save launch butt
    @PostMapping("**/createLaunch")
    public String createLaunch(@ModelAttribute Launch launch) {
        launchService.createLaunch(launch);
        return "redirect:/launches";
    }

    @GetMapping("/showLaunchUpdateForm/{id}")
    public String showLaunchUpdateForm(@PathVariable(value = "id") long id, Model model) {
        //get Launch from Service
        Launch launch = launchService.getLaunchById(id);
        //set launch object to Model
        model.addAttribute("launch", launch);

        return "update_launch";
    }

    @GetMapping("/deleteLaunch/{id}")
    public String deleteLaunch(@PathVariable(value = "id") long id) {
        this.launchService.deleteLaunch(id);
        return "redirect:/launches";
    }

}
