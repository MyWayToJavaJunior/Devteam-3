package com.epam.task6.command.impl.handle;

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

/**
 * This command assign project.
 *
 * Created by olga on 28.04.15.
 */
public class AssignProject extends Command {

    private static AssignProject instance = new AssignProject();

    private static final Logger logger = Logger.getLogger(AssignProject.class);

    /** Logger messages */
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.create.project";
    private static final String MSG_REQUESTED_COMMAND = "logger.activity.requested.order.form";


    /** Attributes and parameters */
    private static final String ATTRIBUTE_USER = "user";
    private static final String ATTRIBUTE_PROJECT_NAME = "project_name";
    private static final String ATTRIBUTE_DEVELOPER_NAME = "dev_name";

    /** Forward page */
    private static final String ASSIG_REDERICT_PAGE = "Controller?executionCommand=SHOW_PROJECTS";
    //project_name
    //dev_name

    public static AssignProject getInstance() {
        return instance;
    }

    /**
     *  Implementation of command that assign project.
     *
     *  @param request HttpServletRequest object
     *  @param response HttpServletResponse object
     *  @return rederict page or command
     *  @throws CommandException  If command can't be executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException{
        User user = (User)request.getSession().getAttribute(ATTRIBUTE_USER);
        String nameProject = request.getParameter(ATTRIBUTE_PROJECT_NAME);
        String devName = request.getParameter(ATTRIBUTE_DEVELOPER_NAME);
        try {
        UserDAOImpl userDAO = UserDAOImpl.getInstance();
        ProjectDAOImpl projectDAO = ProjectDAOImpl.getInstance();

            int projectId = projectDAO.returnIdByName(nameProject);
            int devId = userDAO.getUserByName(devName);

            projectDAO.updateDevId(projectId, devId);
            projectDAO.updateStatusById(projectId, 1);
        }
        catch (DAOException e){
            throw new CommandException(ResourceManager.getProperty(MSG_EXECUTE_ERROR) + user.getId(), e);
        }
        logger.info(ResourceManager.getProperty(MSG_REQUESTED_COMMAND) + user.getId());
        return ASSIG_REDERICT_PAGE;
    }
}

