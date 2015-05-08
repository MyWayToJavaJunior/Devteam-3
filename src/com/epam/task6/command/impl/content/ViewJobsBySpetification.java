package com.epam.task6.command.impl.content;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.JobDAO;
import com.epam.task6.domain.project.Job;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by olga on 04.05.15.
 */
public class ViewJobsBySpetification extends Command {
    public static ViewJobsBySpetification instance = new ViewJobsBySpetification();
    private static Logger logger = Logger.getLogger(ViewJobsBySpetification.class);

    private static final String MSG_REQUESTED_COMMAND = "logger.activity.employee.show.job";
    private static final String MSG_EXECUTE_ERROR = "logger.error.show.current.job";


    private static final String SPETIFICATION_ATTRIBUTE = "spId";
    private static final String JOBS_ATTRIBUTE = "jobs";
    private static final String USER_ATTRIBUTE = "user";
    private static final String MANAGER_PAGE = "jsp/customer/viewJobs.jsp";

    public static ViewJobsBySpetification getInstance() {
        return instance;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = (User)request.getSession().getAttribute(USER_ATTRIBUTE);
        int spetificationId = Integer.parseInt(request.getParameter(SPETIFICATION_ATTRIBUTE));
        JobDAO jobDAO = JobDAO.getInstance();
        try {
            List<Job> jobArrayList = jobDAO.getSpecificationJobs(spetificationId);
            if (jobArrayList != null) {
                request.setAttribute(JOBS_ATTRIBUTE, jobArrayList);
            }
        }
        catch (DAOException e){
            logger.error(ResourceManager.getProperty(MSG_EXECUTE_ERROR)+ user.getId(), e);
        }
        logger.info(ResourceManager.getProperty(MSG_REQUESTED_COMMAND)+user.getId());
        setForward(MANAGER_PAGE);
    }
}
