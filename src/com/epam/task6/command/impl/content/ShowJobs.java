package com.epam.task6.command.impl.content;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by olga on 28.04.15.
 */
public class ShowJobs extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, DAOException {

    }
}
