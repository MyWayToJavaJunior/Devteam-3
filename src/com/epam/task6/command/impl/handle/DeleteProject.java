package com.epam.task6.command.impl.handle;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.ProjectDAOImpl;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by olga on 05.05.15.
 */
public class DeleteProject extends Command {

    private static DeleteProject instance = new DeleteProject();

    /** Initialize activity logger */
    private static Logger logger = Logger.getLogger(DeleteProject.class);

    /** Logger messages */
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.create.order";

    /** Attributes and parameters */
    private static final String ATTRIBUTE_USER = "user";
    private static final String ATTRIBUTE_PROJECT = "projectId";
    private static final String DELETE_PROJECT_PAGE = "Controller?executionCommand=SHOW_PROJECTS";


    public static DeleteProject getInstance() {
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
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws CommandException{
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ATTRIBUTE_USER);
        int projectId = Integer.parseInt(request.getParameter(ATTRIBUTE_PROJECT));
        ProjectDAOImpl projectDAO = ProjectDAOImpl.getInstance();
        try {
            projectDAO.deleteProject(projectId);
        } catch (DAOException e){
            throw new CommandException(ResourceManager.getProperty(MSG_EXECUTE_ERROR) + user.getId(), e);
        }
        return (DELETE_PROJECT_PAGE);

    }
}
