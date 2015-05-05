package com.epam.task6.command.impl.handle;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.ProjectDAO;
import com.epam.task6.domain.project.Project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by olga on 05.05.15.
 */
public class EditProject extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, DAOException {

        Project project = new Project();
        project.setName(request.getParameter("project"));
        project.setTime(request.getParameter("time"));
        project.setId(Integer.parseInt(request.getParameter("id")));
        ProjectDAO projectDAO = ProjectDAO.getInstance();

        projectDAO.updateProjectByName(project.getId(), project.getName());
        projectDAO.updateProjectByTime(project.getId(), project.getTime());

        setForward("Controller?executionCommand=SHOW_PROJECTS");
    }
}
