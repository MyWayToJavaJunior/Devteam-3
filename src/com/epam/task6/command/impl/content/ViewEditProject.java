package com.epam.task6.command.impl.content;

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

/**
 * Created by olga on 05.05.15.
 */
public class ViewEditProject extends Command {
    private static ViewEditProject instance = new ViewEditProject();
    private static Logger logger = Logger.getLogger(ViewEditProject.class);

    private static final String MSG_REQUESTED_COMMAND = "logger.activity.manager.edit.project";
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.edit.project";

    private static final String CUSTOMER_EDIT_PROJECT = "jsp/manager/editProject.jsp";

    private static final String USER_ATTRIBUTE = "user";

    public static ViewEditProject getInstance() {
        return instance;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = (User)request.getSession().getAttribute(USER_ATTRIBUTE);
        int projectId = Integer.parseInt(request.getParameter("projectId"));

        try {
            ProjectDAO projectDAO = ProjectDAO.getInstance();
            Project project = projectDAO.getProjectById(projectId);
            if (project != null) {

                request.setAttribute("project", project);
            }
        }
        catch (DAOException e){
            logger.error(ResourceManager.getProperty(MSG_EXECUTE_ERROR)+ user.getId(), e);
        }
        logger.info(ResourceManager.getProperty(MSG_REQUESTED_COMMAND)+user.getId());
        setForward(CUSTOMER_EDIT_PROJECT);
    }
}
