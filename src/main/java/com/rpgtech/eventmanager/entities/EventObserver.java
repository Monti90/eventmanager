package com.rpgtech.eventmanager.entities;

import java.util.Collection;

public interface EventObserver {
    public void registerObserver(Observer observer);
    public void deleteObserver(Observer observer);
    public void notifyObservers();
}
