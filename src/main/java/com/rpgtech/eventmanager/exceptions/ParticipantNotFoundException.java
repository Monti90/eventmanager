package com.rpgtech.eventmanager.exceptions;

import com.rpgtech.eventmanager.entities.ParticipantEntity;

import java.util.Set;

public class ParticipantNotFoundException extends RuntimeException {
    public ParticipantNotFoundException(String message) { super(message);}
}
