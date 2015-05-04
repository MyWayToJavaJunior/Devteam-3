package com.epam.task6.command.impl.handle;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.controller.JspPageName;
import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.JobDAO;
import com.epam.task6.domain.project.Spetification;
import com.epam.task6.domain.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by olga on 27.04.15.
 */
public class CreateOrderPartTwo extends Command {

    private static final String ATTRIBUTE_SPETIFICATION = "spetification";
    private static final String ATTRIBUTE_USER = "user";

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
        HttpSession session = request.getSession();
        Spetification spetification = (Spetification) session.getAttribute(ATTRIBUTE_SPETIFICATION);
        User user = (User) session.getAttribute(ATTRIBUTE_USER);

        String jobName = request.getParameter(RequestParameterName.NAME_JOB);
       String qualification = request.getParameter(RequestParameterName.QUALIFICATION_JOB);
        String jobTime = request.getParameter(RequestParameterName.JOB_TIME);

        /*
        ArrayList<String> jobName = (ArrayList<String>) session.getAttribute(RequestParameterName.NAME_JOB);
        ArrayList<String> qualification = (ArrayList<String>) session.getAttribute(RequestParameterName.QUALIFICATION_JOB);
        ArrayList<String> jobTime = (ArrayList<String>) session.getAttribute(RequestParameterName.JOB_TIME);
        */

        int countSp = spetification.getJobs();
        JobDAO jobDAO = new JobDAO();
       // for (int i=0; i<jobName.size(); ++i) {
           jobDAO.saveJob(spetification.getId(), jobName, qualification,jobTime);
            System.out.println("111111   "+spetification.getId()+" "+jobName+" "+qualification+" "+jobTime);

       // }

        setForward(JspPageName.CUSTOMER_PAGE);
    }
}
