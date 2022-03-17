package com.rpgtech.eventmanager.services.impl;

import com.rpgtech.eventmanager.repositories.UserRepository;
import com.rpgtech.eventmanager.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;



}
