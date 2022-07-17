package com.rpgtech.eventmanager.services;

import com.rpgtech.eventmanager.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public UserDetails loadUserByUsername(String email);

    public String signUpUser(User user);

    public void enableUser(String email);

}
