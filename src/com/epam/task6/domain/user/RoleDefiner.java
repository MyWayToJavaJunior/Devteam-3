package com.epam.task6.domain.user;

/**
 * Created by olga on 19.04.15.
 */
public class RoleDefiner {

    /**
     * This method defines user role in application
     *
     * @param value Role name from database
     * @return Object role
     */
    public static Role defineRole(String value) {
        Role role = null;
        if (value.equals("customer")) {
            role = Role.CUSTOMER;

        } else if (value.equals("developer")) {
            role = Role.DEVELOPER;

        } else if (value.equals("manager")) {
            role = Role.MANAGER;

        }
        return role;
    }

}
