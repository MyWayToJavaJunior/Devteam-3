package com.epam.task6.command.impl.content;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.ProjectDAOImpl;
import com.epam.task6.dao.impl.SpecificationDAOImpl;
import com.epam.task6.dao.impl.UserDAOImpl;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by olga on 01.05.15.
 */
public class GetProjectForm extends Command {

    private static GetProjectForm instance = new GetProjectForm();
    private static Logger logger = Logger.getLogger("activity");

    /* Attributes and parameters */
    private static final String PARAM_PROJECT = "spetifications";
    private static final String USER_ATTRIBUTE = "user";

    /* Forward page */
    private static final String FORWARD_ORDER_FORM = "jsp/manager/createProject.jsp";

    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.create.order";

    public static GetProjectForm getInstance() {
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
        ProjectDAOImpl projectDAO = ProjectDAOImpl.getInstance();
        SpecificationDAOImpl specificationDAO = SpecificationDAOImpl.getInstance();
        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            List<String> developerName = userDAO.getAllDeveloperNames();
            request.setAttribute("developers", developerName);

            List<String> projectList = projectDAO.getManagerProjectsWithStarus(user.getId(), 0);
            List<String> spetificationList = specificationDAO.getManagerSpetification();
            request.setAttribute(PARAM_PROJECT, spetificationList);
        }

        catch (DAOException e) {
            throw new CommandException(ResourceManager.getProperty(MSG_EXECUTE_ERROR)+ user.getId(), e);
        }

        return (FORWARD_ORDER_FORM);

    }
}
