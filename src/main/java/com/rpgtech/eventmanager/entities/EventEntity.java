package com.rpgtech.eventmanager.entities;

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
    private LocalDateTime startsAt;
    private LocalDateTime endsAt;
    @OneToMany(mappedBy = "event")
    private Set<SessionEntity> agendaItems;
    @ManyToOne
    private OrganizationEntity organization;

}
