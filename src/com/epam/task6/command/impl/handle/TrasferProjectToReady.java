package com.epam.task6.command.impl.handle;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.ProjectDAO;
import com.epam.task6.domain.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by olga on 30.04.15.
 */
public class TrasferProjectToReady extends Command {

    private static final String PROJECT_ATTRIBUTE = "project";
    private static final String USER_ATTRIBUTE = "user";

    private static final String REDERICT = "Controller?command=SHOW_PROJECTS";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, DAOException {

        User user = (User) request.getSession().getAttribute(USER_ATTRIBUTE);
        String projectId = request.getParameter(RequestParameterName.ID_PROJECT);
        ProjectDAO projectDAO = new ProjectDAO();
        System.out.print("projectId   "+projectId);
        projectDAO.updateStatusById(Integer.parseInt(projectId),2);
        setForward(REDERICT);
    }
}
