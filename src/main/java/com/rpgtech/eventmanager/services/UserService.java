package com.rpgtech.eventmanager.services;

import com.rpgtech.eventmanager.entities.EventEntity;
import com.rpgtech.eventmanager.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    public UserDetails loadUserByUsername(String email);

    public String signUpUser(User user);

    public void enableUser(String email);

}
