package com.epam.task6.command;

import com.epam.task6.dao.DAOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract class Command {
    private String forward;
    public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, DAOException;

    public String getForward() {
        return forward;
    }
    public void setForward(String forward) {
        this.forward = forward;
    }
}