package com.rpgtech.eventmanager.services.impl;

import com.rpgtech.eventmanager.email.EmailSender;
import com.rpgtech.eventmanager.entities.User;
import com.rpgtech.eventmanager.entities.UserInfo;
import com.rpgtech.eventmanager.entities.enums.UserRole;
import com.rpgtech.eventmanager.requests.RegistrationRequest;
import com.rpgtech.eventmanager.services.RegistrationService;
import com.rpgtech.eventmanager.services.UserInfoService;
import com.rpgtech.eventmanager.services.UserService;
import com.rpgtech.eventmanager.token.ConfirmationToken;
import com.rpgtech.eventmanager.token.ConfirmationTokenService;
import com.rpgtech.eventmanager.validators.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final EmailValidator emailValidator;
    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;
    private final UserInfoService userInfoService;

    @Override
    public String register(RegistrationRequest request) {
        boolean isEmailValid = emailValidator.test(request.getEmail());
        if(!isEmailValid){
            throw new IllegalStateException("email not valid");
        }
        Long userInfoId = userInfoService.createUserInfo(request.getDiscordName(), request.getDiscordName()).getId();
        String token = userService.signUpUser(
                new User(
                        request.getUsername(),
                        request.getPassword(),
                        request.getEmail(),
                        userInfoService.findUserInfoById(userInfoId),
                        UserRole.USER)
        );
        String link = "http://localhost:8080/api/v1/users/confirm?token=" + token;
        emailSender.send(request.getEmail(), RegistrationService.buildEmail(request.getUsername(), link));
        return token;
    }

    @Override
    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() -> new IllegalStateException("token not found"));
        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }
        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token has expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(
                confirmationToken.getUser().getEmail());
        return "confirmed";
    }

}
