package com.epam.task6.command;

import com.epam.task6.exception.ProjectException;

public class CommandException extends ProjectException {
    private static final long serialVersionUID = 1L;
    public CommandException(String msg){
        super(msg);
    }

    public CommandException(String msg, Exception e){
        super(msg, e);
    }
}