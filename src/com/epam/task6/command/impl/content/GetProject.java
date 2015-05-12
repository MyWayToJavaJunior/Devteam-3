package com.epam.task6.command.impl.content;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.ProjectDAOImpl;
import com.epam.task6.dao.impl.UserDAOImpl;
import com.epam.task6.domain.project.Spetification;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by olga on 01.05.15.
 */
public class GetProject extends Command {
    private static GetProject instance = new GetProject();

    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.order.form";


    private static final String ATTRIBUTE_USER = "user";
    private static final String ATTRIBUTE_SHOW_PROJECT_PAGE = "Controller?executionCommand=SHOW_PROJECTS";
    private static final String ATTRIBUTE_SPETIFICATION = "spec";

    public static GetProject getInstance() {
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

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ATTRIBUTE_USER);
        Spetification spetification = (Spetification) session.getAttribute(ATTRIBUTE_SPETIFICATION);
        String projectName = (String) session.getAttribute("project_name");

        String devName = (String) session.getAttribute("developer_id");

        UserDAOImpl userDAO = UserDAOImpl.getInstance();
        ProjectDAOImpl projectDAO = ProjectDAOImpl.getInstance();
        try {

            projectDAO.saveProject(projectName, user.getId(), spetification.getId(),  userDAO.getUserByName(devName), 0);
        } catch (DAOException e) {
            throw new CommandException(ResourceManager.getProperty(MSG_EXECUTE_ERROR)+ user.getId(), e);
        }
        return (ATTRIBUTE_SHOW_PROJECT_PAGE);
    }
}
