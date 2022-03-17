package com.rpgtech.eventmanager.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class EventEntity implements Serializable {

    private Long id;
    private LocalDate date;
    private ScenarioEntity scenario;
    //TODO - Think about relations between event and players

}
