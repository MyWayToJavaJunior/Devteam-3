package com.epam.task6.service.observer.language;

import com.epam.task6.domain.user.User;

import java.util.EventObject;

/**
 * Created by olga on 30.04.15.
 */
public class LanguageEvent extends EventObject {

    /**
     * Constructor
     *
     * @param user User object
     */
    public LanguageEvent(User user) {
        super(user);
    }

    /**
     * Return changed User object
     *
     * @return User object
     */
    @Override
    public User getSource() {
        return (User)super.getSource();
    }

}
