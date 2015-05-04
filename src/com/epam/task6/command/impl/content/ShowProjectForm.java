package com.epam.task6.command.impl.content;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.ProjectDAO;
import com.epam.task6.dao.impl.SpecificationDAO;
import com.epam.task6.dao.impl.UserDAO;
import com.epam.task6.domain.user.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by olga on 01.05.15.
 */
public class ShowProjectForm extends Command {

    private static Logger logger = Logger.getLogger("activity");

    /* Attributes and parameters */
    private static final String PARAM_PROJECT = "spetifications";
    private static final String USER_ATTRIBUTE = "user";

    /* Forward page */
    private static final String FORWARD_ORDER_FORM = "jsp/manager/createProject.jsp";

    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.create.order";




    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, DAOException {
        User user = (User)request.getSession().getAttribute(USER_ATTRIBUTE);
        ProjectDAO projectDAO = new ProjectDAO();
        SpecificationDAO specificationDAO = new SpecificationDAO();
        UserDAO userDAO = new UserDAO();
        List<String> projectList = projectDAO.getManagerProjectsWithStarus(user.getId(), 0);
        List<String> spetificationList = specificationDAO.getManagerSpetification();
        request.setAttribute(PARAM_PROJECT, spetificationList);
        setForward(FORWARD_ORDER_FORM);

    }
}
