package com.epam.task6.service.checker;

import org.apache.log4j.Logger;

/**
 * Created by olga on 30.04.15.
 */
public class LoginChecker {
    private static Logger logger = Logger.getLogger("activity");

    public static boolean isCorrect(String email, String password) {
        if (email.length() == 0 || password.length() == 0) {
            return false;
        }
        String userPassword = password;
        logger.info("user password: " + password + " password from db: " + userPassword);
        return userPassword.equals(password);
    }
}
