package com.epam.task6.command.impl.handle;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.ProjectDAOImpl;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This command transfer project ro current.
 *
 * Created by olga on 29.04.15.
 */
public class TrasferProjectToCurrent extends Command {

    private static TrasferProjectToCurrent instance = new TrasferProjectToCurrent();

    private static final Logger logger = Logger.getLogger(TrasferProjectToCurrent.class);

    /** Logger messages */
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.create.order";
    private static final String MSG_REQUESTED_COMMAND = "logger.activity.requested.order.form";

    /** Attributes and parameters */
    private static final String USER_ATTRIBUTE = "user";

    /** Forward page */
    private static final String CURRENT_PROJECT_PAGE = "Controller?executionCommand=SHOW_CURRENT_PROJECTS";

    public static TrasferProjectToCurrent getInstance() {
        return instance;
    }

    /**
     *  Implementation of command that transfer project ro current.
     *
     *  @param request HttpServletRequest object
     *  @param response HttpServletResponse object
     *  @return rederict page or command
     *  @throws CommandException  If command can't be executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        User user = (User) request.getSession().getAttribute(USER_ATTRIBUTE);
        String projectId = request.getParameter(RequestParameterName.ID_PROJECT);
        ProjectDAOImpl projectDAO = ProjectDAOImpl.getInstance();
        try {
            projectDAO.updateStatusById(Integer.parseInt(projectId),1);
        } catch (DAOException e) {
            throw new CommandException(ResourceManager.getProperty(MSG_EXECUTE_ERROR) + user.getId(), e);
        }
        logger.info(ResourceManager.getProperty(MSG_REQUESTED_COMMAND) + user.getId());
        return (CURRENT_PROJECT_PAGE);
    }
}
