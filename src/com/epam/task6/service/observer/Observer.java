package com.epam.task6.service.observer;

import java.util.EventObject;

/**
 * Created by olga on 30.04.15.
 */
public interface Observer {
    /**
     * Need to be overridden in concrete observers.
     *
     * @param event May be instance of EventObject class
     */
    public void handle(EventObject event);
}

