package com.epam.task6.command.impl.handle;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.ProjectDAOImpl;
import com.epam.task6.dao.impl.UserDAOImpl;
import com.epam.task6.domain.project.Spetification;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This command create project.
 *
 * Created by olga on 01.05.15.
 */
public class CreateProject extends Command {
    private static CreateProject instance = new CreateProject();

    private static Logger logger = Logger.getLogger(CreateProject.class);

    /** Logger messages */
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.order.form";
    private static final String MSG_REQUESTED_COMMAND = "logger.activity.requested.order.form";

    /** Attributes and parameters */
    private static final String ATTRIBUTE_USER = "user";
    private static final String ATTRIBUTE_SPETIFICATION = "spec";
    private static final String ATTRIBUTE_PROJECT_NAME = "project_name";
    private static final String ATTRIBUTE_DEVELOPER_ID = "developer_id";

    /** Forward page */
    private static final String ATTRIBUTE_SHOW_PROJECT_PAGE = "Controller?executionCommand=SHOW_PROJECTS";

    public static CreateProject getInstance() {
        return instance;
    }

    /**
     *  Implementation of command that create project.
     *
     *  @param request HttpServletRequest object
     *  @param response HttpServletResponse object
     *  @return rederict page or command
     *  @throws CommandException  If command can't be executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ATTRIBUTE_USER);
        Spetification spetification = (Spetification) session.getAttribute(ATTRIBUTE_SPETIFICATION);
        String projectName = (String) session.getAttribute(ATTRIBUTE_PROJECT_NAME);

        String devName = (String) session.getAttribute(ATTRIBUTE_DEVELOPER_ID);

        UserDAOImpl userDAO = UserDAOImpl.getInstance();
        ProjectDAOImpl projectDAO = ProjectDAOImpl.getInstance();
        try {

            projectDAO.saveProject(projectName, user.getId(), spetification.getId(),  userDAO.getUserByName(devName), 0);
        } catch (DAOException e) {
            throw new CommandException(ResourceManager.getProperty(MSG_EXECUTE_ERROR)+ user.getId(), e);
        }
        logger.info(ResourceManager.getProperty(MSG_REQUESTED_COMMAND) + user.getId());
        return (ATTRIBUTE_SHOW_PROJECT_PAGE);
    }
}
