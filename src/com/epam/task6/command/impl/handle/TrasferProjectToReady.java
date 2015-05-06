package com.epam.task6.command.impl.handle;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.ProjectDAO;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by olga on 30.04.15.
 */
public class TrasferProjectToReady extends Command {

    private static final String PROJECT_ATTRIBUTE = "project";
    private static final String USER_ATTRIBUTE = "user";
    private static final String READY_PROJECT_PAGE = "Controller?executionCommand=SHOW_CURRENT_PROJECTS";
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.create.order";

    private static final String REDERICT_TO_READY_PROJECTS = "Controller?executionCommand=SHOW_READY_PROJECTS";


    /**
     *  This method executes the command.
     *
     *  @param request HttpServletRequest object
     *  @param response HttpServletResponse object
     *  @throws CommandException  If command can't be executed.
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        User user = (User) request.getSession().getAttribute(USER_ATTRIBUTE);
        String projectId = request.getParameter(RequestParameterName.ID_PROJECT);
        ProjectDAO projectDAO = ProjectDAO.getInstance();
        try {
            projectDAO.updateStatusById(Integer.parseInt(projectId),2);
        } catch (DAOException e) {
            throw new CommandException(ResourceManager.getProperty(MSG_EXECUTE_ERROR) + user.getId(), e);
        }
        setForward(REDERICT_TO_READY_PROJECTS);
    }
}
