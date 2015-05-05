package com.epam.task6.command.impl.handle;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.SpecificationDAO;
import com.epam.task6.domain.project.Spetification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by olga on 04.05.15.
 */
public class EditOrder extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws CommandException, DAOException {
        Spetification spetification = new Spetification();
        spetification.setName(request.getParameter("order"));
        spetification.setJobs(Integer.parseInt(request.getParameter("job")));
        spetification.setId(Integer.parseInt(request.getParameter("id")));

        System.out.println(request.getParameter("id"));
        System.out.println(request.getParameter("job"));
        System.out.println(request.getParameter("order"));
        SpecificationDAO specificationDAO = SpecificationDAO.getInstance();

        specificationDAO.updateSpetificationName(spetification.getName(),spetification.getId());
        specificationDAO.updateSpetificationJobs(spetification.getJobs(), spetification.getId());

        setForward("Controller?executionCommand=SHOW_SPECIFICATIONS");
    }
}
