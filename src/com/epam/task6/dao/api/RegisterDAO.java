package com.epam.task6.dao.api;

/**
 * Created by olga on 09.05.15.
 */
public interface RegisterDAO {
    public void registerUser(String email, String password, String firstName, String secondName);
}
