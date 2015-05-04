package com.epam.task6.command.impl.handle;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.ProjectDAO;
import com.epam.task6.domain.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by olga on 01.05.15.
 */
public class CreateProject extends Command {

    private static final String ATTRIBUTE_USER = "user";
    private static final String ATTRIBUTE_SPETIFICATION_NAME = "sp_name";
    private static final String ATTRIBUTE_SPETIFICATION = "spetification";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, DAOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ATTRIBUTE_USER);

        ProjectDAO projectDAO = new ProjectDAO();
        //projectDAO.saveProject();
    }
}
