package com.epam.task6.command.impl.handle;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.SpecificationDAOImpl;
import com.epam.task6.domain.project.Spetification;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This command create order.
 *
 * Created by olga on 26.04.15.
 */
public class CreateOrder extends Command {

    private static CreateOrder instance = new CreateOrder();
    private static Logger logger = Logger.getLogger(CreateOrder.class);

    /** Logger messages */
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.create.order";
    private static final String MSG_REQUESTED_COMMAND = "logger.activity.requested.order.form";

    /** Attributes and parameters */
    private static final String ATTRIBUTE_USER = "user";
    private static final String ATTRIBUTE_JOB = "job";
    private static final String ATTRIBUTE_SPETIFICATION = "spetification";
    public static final String ERROR_PAGE = "jsp/error/500.jsp";


    /** Forward page */
    private static final String CUSTOMER_PAGE = "Controller?executionCommand=SHOW_SPECIFICATIONS";

    public static CreateOrder getInstance() {
        return instance;
    }

    /**
     *  Implementation of command that create order.
     *
     *  @param request HttpServletRequest object
     *  @param response HttpServletResponse object
     *  @return rederict page or command
     *  @throws CommandException  If command can't be executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String name = request.getParameter(RequestParameterName.NAME_ORDER);
        String jobs = request.getParameter(RequestParameterName.JOB_NUMBER);

        SpecificationDAOImpl specificationDAO = SpecificationDAOImpl.getInstance();

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ATTRIBUTE_USER);
        try {
            specificationDAO.addSpetificationByNameAndJob(name, Integer.parseInt(jobs), user.getId(), 0);
            session.setAttribute(ATTRIBUTE_JOB, Integer.parseInt(jobs));
            Spetification spetification = specificationDAO.setSpetificationByNameAndJob(name, Integer.parseInt(jobs));
            session.setAttribute(ATTRIBUTE_SPETIFICATION, spetification);
        }
        catch (DAOException e){
            throw new CommandException(ResourceManager.getProperty(MSG_EXECUTE_ERROR) + user.getId(), e);
        }
        catch (NumberFormatException e)
        {
          // session.invalidate();
            return ERROR_PAGE;
        }

        logger.info(ResourceManager.getProperty(MSG_REQUESTED_COMMAND) + user.getId());
        return CUSTOMER_PAGE;
    }

}
