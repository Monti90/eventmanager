package com.rpgtech.eventmanager.builders;

import com.rpgtech.eventmanager.entities.EventEntity;
import com.rpgtech.eventmanager.entities.ScenarioEntity;
import com.rpgtech.eventmanager.entities.SessionEntity;
import com.rpgtech.eventmanager.entities.UserInfo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SessionBuilder {

    private final String meetingLink;
    private final UserInfo host;
    private final LocalDateTime startTime;
    private EventEntity event;
    private ScenarioEntity scenario;



    public SessionBuilder(String link, UserInfo host, LocalDateTime startTime, ScenarioEntity scenario){
        this.meetingLink = link;
        this.host = host;
        this.startTime = startTime;
        this.scenario = scenario;
    }

    public SessionBuilder event(EventEntity event) {
        this.event = event;
        return this;
    }

    public SessionEntity build() {
        SessionEntity session = new SessionEntity(this);
        return session;
    }
}
