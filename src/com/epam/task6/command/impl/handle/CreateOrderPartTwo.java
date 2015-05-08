package com.epam.task6.command.impl.handle;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.controller.JspPageName;
import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.JobDAO;
import com.epam.task6.domain.project.Spetification;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by olga on 27.04.15.
 */
public class CreateOrderPartTwo extends Command {

    private static CreateOrderPartTwo instance = new CreateOrderPartTwo();

    /** Initialize activity logger */
    private static Logger logger = Logger.getLogger(CreateOrderPartTwo.class);

    /** Logger messages */
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.create.order";
    private static final String ATTRIBUTE_SPETIFICATION = "spetification";
    private static final String ATTRIBUTE_USER = "user";

    public static CreateOrderPartTwo getInstance() {
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
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();
        Spetification spetification = (Spetification) session.getAttribute(ATTRIBUTE_SPETIFICATION);
        User user = (User) session.getAttribute(ATTRIBUTE_USER);

        String jobName = request.getParameter(RequestParameterName.NAME_JOB);
        String qualification = request.getParameter(RequestParameterName.QUALIFICATION_JOB);
        String jobTime = request.getParameter(RequestParameterName.JOB_TIME);

        try {
            int countSp = spetification.getJobs();
            JobDAO jobDAO = JobDAO.getInstance();
            jobDAO.saveJob(spetification.getId(), jobName, qualification, jobTime);
            System.out.println("111111   " + spetification.getId() + " " + jobName + " " + qualification + " " + jobTime);
        }
        catch (DAOException e){
            throw new CommandException(ResourceManager.getProperty(MSG_EXECUTE_ERROR) + user.getId(), e);
        }

        setForward(JspPageName.CUSTOMER_PAGE);
    }
}
