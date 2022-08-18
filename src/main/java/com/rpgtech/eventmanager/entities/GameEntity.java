package com.rpgtech.eventmanager.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name="games")
@NoArgsConstructor
@AllArgsConstructor
public class GameEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long Id;
    private String name;

    public GameEntity(String gameName){ this.name = gameName;}
}
