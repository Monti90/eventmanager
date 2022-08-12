package com.rpgtech.eventmanager.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "events")
public class EventEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    @Column(nullable = false)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime startsAt;
    @Column(nullable = false)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime endsAt;
    @ManyToOne
    private OrganizationEntity organization;
    private String description;
    private boolean isActive;

}
