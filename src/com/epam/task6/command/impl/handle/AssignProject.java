package com.epam.task6.command.impl.handle;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.ProjectDAO;
import com.epam.task6.dao.impl.UserDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by olga on 28.04.15.
 */
public class AssignProject extends Command {
    /** Initialize activity logger */
    private static Logger logger = Logger.getLogger("activity");

    /** Logger messages */
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.create.project";

    /** Attributes and parameters */
    private static final String ATTRIBUTE_USER = "user";
    private static final String PARAM_SPECIFICATION_ID = "specId";
    private static final String ATTRIBUTE_FORM_CORRECT = "isFormCorrect";
    private static final String FORWARD_CUSTOMER_PROJECTS = "redirect.manager.projects";
    private static final String FORWARD_PREPARE_PROJECT = "redirect.manager.prepare.project";

    private static final String ATTRIBUTE_PROJECT_NAME = "project_name";
    private static final String ATTRIBUTE_DEVELOPER_NAME = "dev_name";
    //project_name
    //dev_name

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
        String nameProject = request.getParameter(ATTRIBUTE_PROJECT_NAME);
        String devName = request.getParameter(ATTRIBUTE_DEVELOPER_NAME);

        UserDAO userDAO = UserDAO.getInstance();
        ProjectDAO projectDAO = ProjectDAO.getInstance();
        int projectId = projectDAO.returnIdByName(nameProject);
        int devId = userDAO.returnIdByName(devName);

        projectDAO.updateDevId(projectId, devId);
        projectDAO.updateStatusById(projectId, 1);

        System.out.println("assign project  =  " + nameProject +"   "+projectId+ "  "+devId+"  " + devName);
        setForward("Controller?executionCommand=SHOW_PROJECTS");
    }
}

