package com.epam.task6.command.impl.content;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.SpecificationDAO;
import com.epam.task6.domain.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by olga on 01.05.15.
 */
public class ShowProjectFormDetails extends Command {
    private static final String ATTRIBUTE_USER = "user";
    private static final String ATTRIBUTE_SPETIFICATION = "spetification";

    private static final String ATTRIBUTE_SPETIFICATION_NAME = "spetification_name";
    private static final String ATTRIBUTE_JOB = "jobList";

    private static final String FORWARD_ORDER_FORM = "jsp/manager/createProjectDetails.jsp";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, DAOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ATTRIBUTE_USER);
        String spetificationName = request.getParameter(ATTRIBUTE_SPETIFICATION_NAME);
        session.setAttribute(ATTRIBUTE_SPETIFICATION_NAME, spetificationName);

        System.out.print("122323    "+spetificationName);
        SpecificationDAO specificationDAO = new SpecificationDAO();
       // Spetification spetification = specificationDAO.getSpetificationByName(spetificationName);
       // session.setAttribute(ATTRIBUTE_SPETIFICATION, spetification);
//        System.out.println("SP ID IS  " + spetification.getId());

      //  JobDAO jobDAO = new JobDAO();
      //  List<Job> jobList = jobDAO.getSpecificationJobs(spetification.getId());
      //  session.setAttribute(ATTRIBUTE_JOB, jobList);

        setForward(FORWARD_ORDER_FORM);
    }
}
