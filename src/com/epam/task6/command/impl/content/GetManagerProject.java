package com.epam.task6.command.impl.content;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.ProjectDAOImpl;
import com.epam.task6.domain.project.Project;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * This command get all managed projects.
 *
 * Created by olga on 28.04.15.
 */
public class GetManagerProject extends Command {
    private static GetManagerProject instance = new GetManagerProject();
    private static final Logger logger = Logger.getLogger(GetManagerProject.class);

    /** Logger messages */
    private static final String MSG_REQUESTED_COMMAND = "logger.activity.manager.managed.show.project";
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.view.project";

    /** Attributes and parameters */
    private static final String PROJECT_ATTRIBUTE = "project";
    private static final String USER_ATTRIBUTE = "user";

    /** Forward pages */
    private static final String MANAGER_PAGE = "jsp/manager/projects.jsp";

    public static GetManagerProject getInstance() {
        return instance;
    }

    /**
     *  Implementation of command that get all managed projects.
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @return rederict page or command
     * @throws CommandException If execution is failed
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        User user = (User)request.getSession().getAttribute(USER_ATTRIBUTE);
        ProjectDAOImpl projectDAO = ProjectDAOImpl.getInstance();
        try {
            HttpSession httpSession = request.getSession();
            List<Project> projectList = projectDAO.getManagerProjects(user.getId());
            httpSession.setAttribute(PROJECT_ATTRIBUTE, projectList);

            if (null != projectList) {
                request.setAttribute(RequestParameterName.SIMPLE_INFO, projectList);
            }
        }
        catch (DAOException e){
            throw new CommandException(ResourceManager.getProperty(MSG_EXECUTE_ERROR) + user.getId(), e);
        }
        logger.info(ResourceManager.getProperty(MSG_REQUESTED_COMMAND)+user.getId());
        return (MANAGER_PAGE);
    }
}
