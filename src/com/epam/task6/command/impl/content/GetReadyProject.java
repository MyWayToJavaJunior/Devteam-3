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
 * Implementing command pattern.
 *
 * Created by olga on 29.04.15.
 */
public class GetReadyProject extends Command {
    private static GetReadyProject instance = new GetReadyProject();
    private static Logger logger = Logger.getLogger(GetReadyProject.class);

    private static final String MSG_REQUESTED_COMMAND = "logger.activity.manager.managed.show.project";
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.view.project";


    private static final String USER_ATTRIBUTE = "user";
    private static final String NEW_PROJECT_PAGE = "jsp/developer/newProjects.jsp";


    public static GetReadyProject getInstance() {
        return instance;
    }

    /**
     *  This method executes the command.
     *
     *  @param request HttpServletRequest object
     *  @param response HttpServletResponse object
     *  @throws CommandException  If command can't be executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = (User)request.getSession().getAttribute(USER_ATTRIBUTE);
        ProjectDAOImpl projectDAO = ProjectDAOImpl.getInstance();
        try {
            List<Project> projectList = projectDAO.getProjectsByStatusAndDivId(2 ,user.getId());
            if (null != projectList) {
                request.setAttribute(RequestParameterName.SIMPLE_INFO, projectList);
            }
        }
        catch (DAOException e){
            throw new CommandException(ResourceManager.getProperty(MSG_EXECUTE_ERROR) + user.getId(), e);
        }
        logger.info(ResourceManager.getProperty(MSG_REQUESTED_COMMAND)+user.getId());
        return (NEW_PROJECT_PAGE);
    }
}