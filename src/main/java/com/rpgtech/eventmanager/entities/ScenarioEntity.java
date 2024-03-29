package com.rpgtech.eventmanager.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name="scenarios")
@NoArgsConstructor
@AllArgsConstructor
public class ScenarioEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long scenarioID;
    private String title;
    @ManyToOne
    private UserInfo userInfo;
    @ManyToOne
    private GameEntity game;
    private int players;
    private String description;
    private Long durationHours;
    private Long durationMinutes;
}
