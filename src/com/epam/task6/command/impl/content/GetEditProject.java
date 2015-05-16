package com.epam.task6.command.impl.content;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.ProjectDAOImpl;
import com.epam.task6.domain.project.Project;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This command get page for edit project.
 *
 * Created by olga on 05.05.15.
 */
public class GetEditProject extends Command {
    private static GetEditProject instance = new GetEditProject();
    private static final Logger logger = Logger.getLogger(GetEditProject.class);

    private static final String MSG_REQUESTED_COMMAND = "logger.activity.manager.edit.project";
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.edit.project";

    private static final String CUSTOMER_EDIT_PROJECT = "jsp/manager/editProject.jsp";

    private static final String USER_ATTRIBUTE = "user";
    private static final String ATTRIBUTE_PROJECT_ID = "projectId";
    private static final String ATTRIBUTE_PROJECT = "project";


    public static GetEditProject getInstance() {
        return instance;
    }

    /**
     * This method invalidates user session
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws CommandException If execution is failed
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = (User)request.getSession().getAttribute(USER_ATTRIBUTE);
        int projectId = Integer.parseInt(request.getParameter(ATTRIBUTE_PROJECT_ID));

        try {
            ProjectDAOImpl projectDAO = ProjectDAOImpl.getInstance();
            Project project = projectDAO.getProjectById(projectId);
            if (project != null) {

                request.setAttribute(ATTRIBUTE_PROJECT, project);
            }
        }
        catch (DAOException e){
            throw new CommandException(ResourceManager.getProperty(MSG_EXECUTE_ERROR)+ user.getId(), e);
        }
        logger.info(ResourceManager.getProperty(MSG_REQUESTED_COMMAND)+user.getId());
        return(CUSTOMER_EDIT_PROJECT);
    }
}
