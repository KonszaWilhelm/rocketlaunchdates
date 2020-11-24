package com.launchdatesandshop.service;

import com.launchdatesandshop.dto.UserRegistrationDto;
import com.launchdatesandshop.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {


    User saveUser(UserRegistrationDto registrationDto);
    public User getUserByEmail(String email);



}
