package com.epam.task6.command.impl.content;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.ProjectDAOImpl;
import com.epam.task6.dao.impl.UserDAOImpl;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This command is get form for assign project to developer.
 *
 * Created by olga on 30.04.15.
 */
public class GetAssignProjectForm extends Command {
    private static GetAssignProjectForm instance = new GetAssignProjectForm();

    private static final Logger logger = Logger.getLogger(GetAssignProjectForm.class);

    /** Logger messages */
    private static final String MSG_REQUESTED_COMMAND = "logger.activity.manager.view.assign.project";
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.view.assign.project";

    /** Attributes */
    private static final String PARAM_PROJECT = "projects";
    private static final String USER_ATTRIBUTE = "user";
    private static final String PARAM_DEV_NAMES = "dev_names";

    /** Forward pages */
    private static final String FORWARD_ASSIGN_PROJECT_FORM = "jsp/manager/assignProject.jsp";

    public static GetAssignProjectForm getInstance() {
        return instance;
    }

    /**
     *  Implementation of command that get assign project form
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws CommandException If execution is failed
     */

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = (User)request.getSession().getAttribute(USER_ATTRIBUTE);
        try {
            ProjectDAOImpl projectDAO = ProjectDAOImpl.getInstance();
            UserDAOImpl userDAO = UserDAOImpl.getInstance();
            List<String> projectList = projectDAO.getManagerProjectsWithStarus(user.getId(), 0);
            List<String> developerName = userDAO.getAllDeveloperNames();
            request.setAttribute(PARAM_PROJECT, projectList);
            request.setAttribute(PARAM_DEV_NAMES, developerName);
        }
        catch (DAOException e){
            throw new CommandException(ResourceManager.getProperty(MSG_EXECUTE_ERROR), e);
        }
        logger.info(ResourceManager.getProperty(MSG_REQUESTED_COMMAND) + user.getId());
        return FORWARD_ASSIGN_PROJECT_FORM;
    }
}
