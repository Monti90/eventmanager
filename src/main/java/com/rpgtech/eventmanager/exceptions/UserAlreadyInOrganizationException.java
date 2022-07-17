package com.rpgtech.eventmanager.exceptions;

public class UserAlreadyInOrganizationException extends RuntimeException {
    public UserAlreadyInOrganizationException(String message) {
        super(message);
    }
}
