package com.epam.task6.command.impl.content;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.SpecificationDAOImpl;
import com.epam.task6.dao.impl.UserDAOImpl;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This command get project form.
 *
 * Created by olga on 01.05.15.
 */
public class GetProjectForm extends Command {

    private static GetProjectForm instance = new GetProjectForm();
    private static final Logger logger = Logger.getLogger(GetProjectForm.class);

    /** Logger messages */
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.create.order";
    private static final String MSG_REQUESTED_COMMAND = "logger.activity.requested.order.form";

    /** Attributes and parameters */
    private static final String PARAM_PROJECT = "spetifications";
    private static final String USER_ATTRIBUTE = "user";
    private static final String ATTRIBUTE_DEVELOPERS = "developers";

    /** Forward page */
    private static final String FORWARD_ORDER_FORM = "jsp/manager/createProject.jsp";


    public static GetProjectForm getInstance() {
        return instance;
    }

    /**
     * Implementation of command that get project form.
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @return rederict page or command
     * @throws CommandException If execution is failed
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = (User)request.getSession().getAttribute(USER_ATTRIBUTE);
        SpecificationDAOImpl specificationDAO = SpecificationDAOImpl.getInstance();
        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            List<String> developerName = userDAO.getAllDeveloperNames();
            request.setAttribute(ATTRIBUTE_DEVELOPERS, developerName);
            List<String> spetificationList = specificationDAO.getManagerSpetification();
            request.setAttribute(PARAM_PROJECT, spetificationList);
        }

        catch (DAOException e) {
            throw new CommandException(ResourceManager.getProperty(MSG_EXECUTE_ERROR)+ user.getId(), e);
        }
        logger.info(ResourceManager.getProperty(MSG_REQUESTED_COMMAND) + user.getId());
        return FORWARD_ORDER_FORM;

    }
}
