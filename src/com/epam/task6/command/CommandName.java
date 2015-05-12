package com.epam.task6.command;

import com.epam.task6.domain.user.Role;

/**
 * Enum of user commands.
 *
 * Created by olga on 30.04.15.
 */

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
    CREATE_ORDER(Role.CUSTOMER),
    CREATE_JOB(Role.CUSTOMER),
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
    EDIT_ORDER (Role.CUSTOMER),
    SHOW_CUSTOMER_JOBS(Role.CUSTOMER),
    VIEW_EDIT_PROJECT(Role.MANAGER),
    EDIT_PRIJECT (Role.MANAGER),
    DELETE_PROJECT (Role.MANAGER),
    VIEW_WAITING_ORDER (Role.MANAGER),
    EDIT_ORDER_DETAIL (Role.CUSTOMER),
    VIEW_USER(Role.MANAGER),
    CREATE_PROJECT (Role.MANAGER);


    private Role type;

    private CommandName(Role type) {
        this.type = type;
    }

    public Role getUserType() {
        return type;
    }
}
