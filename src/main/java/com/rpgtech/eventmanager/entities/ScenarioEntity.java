package com.rpgtech.eventmanager.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name="scenarios")
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
}
