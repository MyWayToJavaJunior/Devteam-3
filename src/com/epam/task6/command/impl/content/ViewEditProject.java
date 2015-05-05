package com.epam.task6.command.impl.content;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.ProjectDAO;
import com.epam.task6.domain.project.Project;
import com.epam.task6.domain.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by olga on 05.05.15.
 */
public class ViewEditProject extends Command {

    private static final String CUSTOMER_EDIT_PROJECT = "jsp/manager/editProject.jsp";

    private static final String USER_ATTRIBUTE = "user";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, DAOException {
        User user = (User)request.getSession().getAttribute(USER_ATTRIBUTE);
        int projectId = Integer.parseInt(request.getParameter("projectId"));

        ProjectDAO projectDAO = ProjectDAO.getInstance();
        Project project = projectDAO.getProjectById(projectId);

        if (project!=null){
            request.setAttribute("project", project);
        }
        setForward(CUSTOMER_EDIT_PROJECT);
    }
}
