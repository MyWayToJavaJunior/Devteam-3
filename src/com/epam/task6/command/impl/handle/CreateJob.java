package com.epam.task6.command.impl.handle;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.JobDAOImpl;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This command create job.
 *
 * Created by olga on 27.04.15.
 */
public class CreateJob extends Command {

    private static CreateJob instance = new CreateJob();

    /** Initialize activity logger */
    private static final Logger logger = Logger.getLogger(CreateJob.class);

    /** Logger messages */
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.create.order";
    private static final String MSG_REQUESTED_COMMAND = "logger.activity.requested.order.form";

    /** Attributes and parameters */
    private static final String ATTRIBUTE_SPETIFICATION = "spId";
    private static final String ATTRIBUTE_USER = "user";

    /** Forward page */
    private static final String CUSTOMER_PAGE = "Controller?executionCommand=SHOW_SPECIFICATIONS";

    public static CreateJob getInstance() {
        return instance;
    }

    /**
     *  Implementation of command that create job.
     *
     *  @param request HttpServletRequest object
     *  @param response HttpServletResponse object
     *  @return rederict page or command
     *  @throws CommandException  If command can't be executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ATTRIBUTE_USER);

        String jobName = request.getParameter(RequestParameterName.NAME_JOB);
        String qualification = request.getParameter(RequestParameterName.QUALIFICATION_JOB);
        String jobTime = request.getParameter(RequestParameterName.JOB_TIME);
        int spId = (int) session.getAttribute(ATTRIBUTE_SPETIFICATION);

        try {
            JobDAOImpl jobDAO = JobDAOImpl.getInstance();
            jobDAO.saveJob(spId, jobName, qualification, jobTime);
        }
        catch (DAOException e){
            throw new CommandException(ResourceManager.getProperty(MSG_EXECUTE_ERROR) + user.getId(), e);
        }
        logger.info(ResourceManager.getProperty(MSG_REQUESTED_COMMAND) + user.getId());
        return CUSTOMER_PAGE;
    }
}
