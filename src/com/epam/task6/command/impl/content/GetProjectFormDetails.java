package com.epam.task6.command.impl.content;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.JobDAOImpl;
import com.epam.task6.dao.impl.ProjectDAOImpl;
import com.epam.task6.dao.impl.SpecificationDAOImpl;
import com.epam.task6.domain.project.Job;
import com.epam.task6.domain.project.Spetification;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by olga on 01.05.15.
 */
public class GetProjectFormDetails extends Command {
    private static GetProjectFormDetails instance = new GetProjectFormDetails();

    private static final Logger logger = Logger.getLogger(GetProjectFormDetails.class);

    /** Logger messages */
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.view.assign.project";
    private static final String MSG_REQUESTED_COMMAND = "logger.activity.requested.order.form";

    /** Attributes and parameters */
    private static final String ATTRIBUTE_USER = "user";
    private static final String ATTRIBUTE_SPETIFICATION = "spetification";
    private static final String ATTRIBUTE_SPETIFICATION_NAME = "spetification_name";
    private static final String ATTRIBUTE_SPETIFICATION_TO_SESSION = "spec";
    private static final String ATTRIBUTE_PROJECT_NAME = "project_name";
    private static final String ATTRIBUTE_JOB = "jobList";
    private static final String ATTRIBUTE_DEV = "developer_id";

    /** Forward page */
    private static final String FORWARD_ORDER_FORM = "jsp/manager/createProjectDetails.jsp";

    public static GetProjectFormDetails getInstance() {
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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException{

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ATTRIBUTE_USER);
        String projectName = request.getParameter(ATTRIBUTE_PROJECT_NAME);
        session.setAttribute(ATTRIBUTE_PROJECT_NAME, projectName);

        String devName = request.getParameter(ATTRIBUTE_DEV);;
        session.setAttribute(ATTRIBUTE_DEV, devName);

        String spetificationName = request.getParameter(ATTRIBUTE_SPETIFICATION_NAME);
        System.out.println(projectName+"   "+spetificationName);
        session.setAttribute(ATTRIBUTE_SPETIFICATION_NAME, spetificationName);

        try {
            SpecificationDAOImpl specificationDAO = SpecificationDAOImpl.getInstance();
            Spetification spetification = specificationDAO.getSpetificationByName(spetificationName);
            session.setAttribute(ATTRIBUTE_SPETIFICATION, spetification);

            session.setAttribute(ATTRIBUTE_SPETIFICATION_TO_SESSION, spetification);
            JobDAOImpl jobDAO = JobDAOImpl.getInstance();
            List<Job> jobList = jobDAO.getSpecificationJobs(spetification.getId());
            session.setAttribute(ATTRIBUTE_JOB, jobList);
            ProjectDAOImpl projectDAO = ProjectDAOImpl.getInstance();
        }
        catch (DAOException e){
            throw new CommandException(ResourceManager.getProperty(MSG_EXECUTE_ERROR), e);
        }

        logger.info(ResourceManager.getProperty(MSG_REQUESTED_COMMAND) + user.getId());
        return FORWARD_ORDER_FORM;
    }
}
