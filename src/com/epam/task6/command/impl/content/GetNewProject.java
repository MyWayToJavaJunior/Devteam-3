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
import java.util.List;

/**
 * This command get all new projects with status 0.
 *
 * Created by olga on 29.04.15.
 */
public class GetNewProject extends Command {
    public static GetNewProject instance = new GetNewProject();
    private static Logger logger = Logger.getLogger(GetNewProject.class);

    private static final String MSG_REQUESTED_COMMAND = "logger.activity.employee.show.new.project";
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.view.new.project";


    private static final String USER_ATTRIBUTE = "user";
    private static final String NEW_PROJECT_PAGE = "jsp/developer/newProjects.jsp";

    public static GetNewProject getInstance() {
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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException{

        User user = (User)request.getSession().getAttribute(USER_ATTRIBUTE);
        ProjectDAOImpl projectDAO = ProjectDAOImpl.getInstance();
        try {
            List<Project> projectList = projectDAO.getProjectsByStatusAndDivId(0, user.getId());
            if (null != projectList) {
                request.setAttribute(RequestParameterName.SIMPLE_INFO, projectList);
            }
        }
        catch (DAOException e){
            throw new CommandException(ResourceManager.getProperty(MSG_EXECUTE_ERROR)+ user.getId(), e);
        }
        logger.info(ResourceManager.getProperty(MSG_REQUESTED_COMMAND)+user.getId());
        return (NEW_PROJECT_PAGE);

    }
}
