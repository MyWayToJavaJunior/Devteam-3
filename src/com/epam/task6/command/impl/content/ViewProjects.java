package com.epam.task6.command.impl.content;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.ProjectDAO;
import com.epam.task6.domain.project.Project;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * Created by olga on 28.04.15.
 */
public class ViewProjects extends Command {

    private static Logger logger = Logger.getLogger("activity");

    private static final String MSG_REQUESTED_COMMAND = "logger.activity.manager.managed.show.project";
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.show.order";


    private static final String PROJECT_ATTRIBUTE = "project";
    private static final String USER_ATTRIBUTE = "user";
    private static final String MANAGER_PAGE = "jsp/manager/projects.jsp";


    /**
     * This method executes the command.
     *
     * @param request HttpServletRequest object
     * @return page or forward command.
     * @throws CommandException  If command can't be executed.
     * @throws DAOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, DAOException {

        User user = (User)request.getSession().getAttribute(USER_ATTRIBUTE);
        ProjectDAO projectDAO = ProjectDAO.getInstance();
        try {
            HttpSession httpSession = request.getSession();

            List<Project> projectList = projectDAO.getManagerProjects(user.getId());
            httpSession.setAttribute(PROJECT_ATTRIBUTE, projectList);


            if (null != projectList) {
                request.setAttribute(RequestParameterName.SIMPLE_INFO, projectList);
            }
        }
        catch (DAOException e){
            logger.error(ResourceManager.getProperty(MSG_EXECUTE_ERROR)+ user.getId(), e);
        }
        logger.info(ResourceManager.getProperty(MSG_REQUESTED_COMMAND)+user.getId());
        setForward(MANAGER_PAGE);
    }
}
