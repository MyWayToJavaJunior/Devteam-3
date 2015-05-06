package com.epam.task6.command.impl.content;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.ProjectDAO;
import com.epam.task6.dao.impl.UserDAO;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by olga on 30.04.15.
 */
public class ViewAssignProjectForm extends Command {
    private static Logger logger = Logger.getLogger(ViewAssignProjectForm.class);

    private static final String MSG_REQUESTED_COMMAND = "logger.activity.manager.view.assign.project";
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.view.assign.project";

    private static final String PARAM_PROJECT = "projects";
    private static final String USER_ATTRIBUTE = "user";
    private static final String PARAM_DEV_NAMES = "dev_names";

    private static final String FORWARD_ASSIGN_PROJECT_FORM = "jsp/manager/assignProject.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = (User)request.getSession().getAttribute(USER_ATTRIBUTE);
        try {
            ProjectDAO projectDAO = ProjectDAO.getInstance();
            UserDAO userDAO = UserDAO.getInstance();
            List<String> projectList = projectDAO.getManagerProjectsWithStarus(user.getId(), 0);
            List<String> developerName = userDAO.getAllDeveloperNames();
            request.setAttribute(PARAM_PROJECT, projectList);
            request.setAttribute(PARAM_DEV_NAMES, developerName);
        }
        catch (DAOException e){
            throw new CommandException(ResourceManager.getProperty(MSG_EXECUTE_ERROR), e);
        }
        logger.info(ResourceManager.getProperty(MSG_REQUESTED_COMMAND) + user.getId());
        setForward(FORWARD_ASSIGN_PROJECT_FORM);
    }
}
