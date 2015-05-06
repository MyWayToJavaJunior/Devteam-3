package com.epam.task6.command.impl.handle;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.ProjectDAO;
import com.epam.task6.domain.project.Project;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by olga on 05.05.15.
 */
public class EditProject extends Command {

    /** Initialize activity logger */
    private static Logger logger = Logger.getLogger(DeleteProject.class);

    /** Logger messages */
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.create.order";

    /** Attributes and parameters */
    private static final String ATTRIBUTE_USER = "user";
    private static final String ATTRIBUTE_PR_NAME = "project";
    private static final String ATTRIBUTE_PR_TIME= "time";
    private static final String ATTRIBUTE_PR_ID = "id";
    private static final String EDIT_PROJECT_PAGE = "Controller?executionCommand=SHOW_PROJECTS";
    /**
    /**
     *  This method executes the command.
     *
     *  @param request HttpServletRequest object
     *  @param response HttpServletResponse object
     *  @throws CommandException  If command can't be executed.
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException{
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ATTRIBUTE_USER);
        Project project = new Project();
        project.setName(request.getParameter(ATTRIBUTE_PR_NAME));
        project.setTime(request.getParameter(ATTRIBUTE_PR_TIME));
        project.setId(Integer.parseInt(request.getParameter(ATTRIBUTE_PR_ID)));
        ProjectDAO projectDAO = ProjectDAO.getInstance();
        try {
            projectDAO.updateProjectByName(project.getId(), project.getName());
            projectDAO.updateProjectByTime(project.getId(), project.getTime());
        }
        catch (DAOException e){
            throw new CommandException(ResourceManager.getProperty(MSG_EXECUTE_ERROR) + user.getId(), e);
        }
        setForward(EDIT_PROJECT_PAGE);
    }
}
