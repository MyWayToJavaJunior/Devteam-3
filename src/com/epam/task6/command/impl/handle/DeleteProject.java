package com.epam.task6.command.impl.handle;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.ProjectDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by olga on 05.05.15.
 */
public class DeleteProject extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws CommandException, DAOException {

        int projectId = Integer.parseInt(request.getParameter("projectId"));
        ProjectDAO projectDAO = ProjectDAO.getInstance();
        projectDAO.deleteProject(projectId);
        setForward("Controller?executionCommand=SHOW_PROJECTS");

    }
}
