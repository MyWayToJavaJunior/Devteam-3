package com.epam.task6.command.impl.handle;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.dao.impl.SpecificationDAO;
import com.epam.task6.domain.project.Spetification;
import com.epam.task6.domain.user.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by olga on 26.04.15.
 */
public class CreateOrderPartOne extends Command {
    /** Initialize activity logger */
    private static Logger logger = Logger.getLogger("activity");

    /** Logger messages */
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.create.order";

    /** Attributes and parameters */
    private static final String ATTRIBUTE_USER = "user";
    private static final String ATTRIBUTE_JOB = "job";
    private static final String ATTRIBUTE_SPETIFICATION = "spetification";
    private static final String CREATE_JOBS = "jsp/customer/addjobs.jsp";

    /**
     * This method executes the command.
     *
     * @param request HttpServletRequest object
     * @return page or forward command.
     * @throws CommandException  If command can't be executed.
     * @throws com.epam.task6.dao.DAOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String name = request.getParameter(RequestParameterName.NAME_ORDER);
        String jobs = request.getParameter(RequestParameterName.JOB_NUMBER);
        SpecificationDAO specificationDAO = SpecificationDAO.getInstance();

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ATTRIBUTE_USER);

        specificationDAO.addSpetificationByNameAndJob(name, Integer.parseInt(jobs), user.getId(), 0);
        session.setAttribute(ATTRIBUTE_JOB, Integer.parseInt(jobs));
        Spetification spetification = specificationDAO.setSpetificationByNameAndJob(name, Integer.parseInt(jobs));
        session.setAttribute(ATTRIBUTE_SPETIFICATION, spetification);
        setForward(CREATE_JOBS);
    }

}
