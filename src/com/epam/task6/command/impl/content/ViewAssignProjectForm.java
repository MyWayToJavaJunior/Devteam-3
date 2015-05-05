package com.epam.task6.command.impl.content;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.ProjectDAO;
import com.epam.task6.dao.impl.UserDAO;
import com.epam.task6.domain.user.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by olga on 30.04.15.
 */
public class ViewAssignProjectForm extends Command {
    private static Logger logger = Logger.getLogger(ViewAssignProjectForm.class);

    private static final String PARAM_PROJECT = "projects";
    private static final String USER_ATTRIBUTE = "user";
    private static final String PARAM_DEV_NAMES = "dev_names";

    private static final String FORWARD_ORDER_FORM = "jsp/manager/assignProject.jsp";
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.create.order";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, DAOException {
        User user = (User)request.getSession().getAttribute(USER_ATTRIBUTE);
        ProjectDAO projectDAO = new ProjectDAO();
        UserDAO userDAO = UserDAO.getInstance();
        List<String> projectList = projectDAO.getManagerProjectsWithStarus(user.getId(), 0);
        List<String> developerName = userDAO.getAllDeveloperNames();
        request.setAttribute(PARAM_PROJECT, projectList);
        request.setAttribute(PARAM_DEV_NAMES, developerName);

        setForward(FORWARD_ORDER_FORM);
    }
}
