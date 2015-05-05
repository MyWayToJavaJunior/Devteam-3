package com.epam.task6.command.impl.content;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.JobDAO;
import com.epam.task6.domain.project.Job;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by olga on 04.05.15.
 */
public class ViewJobsBySpetification extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, DAOException {
        int spetificationId = Integer.parseInt(request.getParameter("spId"));
        JobDAO jobDAO = JobDAO.getInstance();
        List<Job> jobArrayList = jobDAO.getSpecificationJobs(spetificationId);
        if (jobArrayList != null){
            request.setAttribute("jobs", jobArrayList);
        }
        setForward("jsp/customer/viewJobs.jsp");
    }
}
