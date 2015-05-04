package com.epam.task6.command.impl.handle;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by olga on 29.04.15.
 */
public class EditOrder extends Command {

    /**
     * This method executes the command.
     *
     * @param request HttpServletRequest object
     * @return page or forward command.
     * @throws CommandException  If command can't be executed.
     * @throws DAOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response){
        String p = "lllllll";
    System.out.println(p);
    }
}
