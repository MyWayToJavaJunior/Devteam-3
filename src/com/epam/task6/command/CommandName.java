package com.epam.task6.command;

import com.epam.task6.domain.user.Role;

public enum CommandName {
    NO_SUCH_COMMAND(Role.ALL),
    LOGIN(Role.ALL),
    REGISTER(Role.ALL),
    SHOW_MENU_COMMAND(Role.ALL),
    SHOW_SPECIFICATIONS(Role.ALL),
    SHOW_PROJECTS(Role.MANAGER),
    SHOW_BILLS(Role.CUSTOMER),
    LOGOUT(Role.ALL),
    SHOW_ORDER_FORM(Role.CUSTOMER),
    CREATE_ORDER_PART_ONE(Role.CUSTOMER),
    CREATE_ORDER_PART_TWO(Role.CUSTOMER),
    DEV(Role.DEVELOPER),
    SHOW_NEW_PROJECTS(Role.DEVELOPER),
    SHOW_CURRENT_PROJECTS(Role.DEVELOPER),
    SHOW_READY_PROJECTS(Role.DEVELOPER),
    TRANSFER_NEW_PROJECT(Role.DEVELOPER),
    TRANSFER_CURRENT_PROJECT(Role.DEVELOPER),
    SHOW_ASSIGN_PROJECT_FORM(Role.MANAGER),
    ASSIGN_PROJECT(Role.MANAGER),
    SHOW_PROJECT_FORM (Role.MANAGER),
    SHOW_PROJECT_FORM_DETAILS (Role.MANAGER),
    CHANGE_LANGUAGE(Role.ALL),
    EDIT_ORDER (Role.CUSTOMER);

    private Role type;

    /**
     * Constructor
     *
     * @param type User Role object
     */
    private CommandName(Role type) {
        this.type = type;
    }

    /**
     * Returns user role object
     *
     * @return User role object
     */
    public Role getUserType() {
        return type;
    }
}
