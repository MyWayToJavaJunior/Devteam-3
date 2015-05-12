package com.epam.task6.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract class Command {

    /**
     * Need to be overridden by command
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws CommandException if execution is failed
     */
    public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;

}