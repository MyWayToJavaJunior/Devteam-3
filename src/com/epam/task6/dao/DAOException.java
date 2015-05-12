package com.epam.task6.dao;

/**
 * This class defines exception which throws if on dao layer arises sql errors.
 *
 * @author olga
 * @since 1.0.0-alpha
 */
public class DAOException extends Exception {

    /**
     * Constructor
     *
     * @param s Message
     */
    public DAOException(String s) {
        super(s);
    }

    /**
     * Constructor
     *
     * @param s Message
     * @param throwable Exception object from lower level of business logic
     */

    public DAOException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
