package com.epam.task6.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract class Command {
    private String forward;

    /**
     * Need to be overridden by command
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws CommandException if execution is failed
     */
    public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;

    /**
     * Return forward page path
     *
     * @return Page path
     */
    public String getForward() {
        return forward;
    }

    /**
     * Sets forward page path
     *
     * @param forward Page path
     */
    public void setForward(String forward) {
        this.forward = forward;
    }
}