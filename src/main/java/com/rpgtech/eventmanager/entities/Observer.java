package com.rpgtech.eventmanager.entities;

import java.time.LocalDateTime;
import java.util.Collection;

public interface Observer {
    public void update(LocalDateTime startTime, LocalDateTime endTime, boolean isActive, Collection<?> collection);
}
