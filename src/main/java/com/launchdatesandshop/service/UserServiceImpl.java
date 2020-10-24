package com.launchdatesandshop.service;

import com.launchdatesandshop.dto.UserRegistrationDto;
import com.launchdatesandshop.entities.Product;
import com.launchdatesandshop.entities.Role;
import com.launchdatesandshop.entities.User;
import com.launchdatesandshop.exception.ResourceNotFoundException;
import com.launchdatesandshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    //constructor based injection, should be changed for the others as well
    //injecting password encryptors
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    @Override
    public User saveUser(UserRegistrationDto registrationDto) {
        User user = new User(
                registrationDto.getFirstName(),
                registrationDto.getLastName(),
                registrationDto.getEmail(),
                passwordEncoder.encode(
                        registrationDto.getPassword()),
                registrationDto.getAddress(),
                Arrays.asList(new Role("ROLE_USER")));
        //Role hardcoded, should be changed later

        return userRepository.save(user);
    }

        //////
    @Override
    public User getUserById(long userId) {
        Optional<User> userDb = this.userRepository.findById(userId);

        if (userDb.isPresent()) {
            return userDb.get();
        } else {
            throw new ResourceNotFoundException("Record/product not found with id: " + userId);
        }

    }

        //////
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    //look up the below s no idea what it does
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        //converting roles to authorities

        //converting roles into stream, on top of stream we map a role and convert role to SimpleGrantedAuthority,
        // passed role name as param and Collected a stream into a list
        //try to understand this o_O
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }

}
