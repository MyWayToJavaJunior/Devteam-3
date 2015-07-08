package com.epam.task6.command.impl.handle;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
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
 * This command edit order.
 *
 * Created by olga on 04.05.15.
 */
public class EditOrder extends Command {

    private static EditOrder instance = new EditOrder();
    /** Initialize activity logger */
    private static final Logger logger = Logger.getLogger(DeleteProject.class);

    /** Logger messages */
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.create.order";
    private static final String MSG_REQUESTED_COMMAND = "logger.activity.requested.order.form";

    /** Attributes and parameters */
    private static final String ATTRIBUTE_USER = "user";
    private static final String ATTRIBUTE_SP_NAME = "order";
    private static final String ATTRIBUTE_SP_JOBS= "job";
    private static final String ATTRIBUTE_SP_ID = "id";

    /** Forward page */
    private static final String EDIT_ORDER_PAGE = "Controller?executionCommand=SHOW_SPECIFICATIONS";

    public static EditOrder getInstance() {
        return instance;
    }

    /**
     *  Implementation of command that edit order.
     *
     *  @param request HttpServletRequest object
     *  @param response HttpServletResponse object
     *  @return rederict page or command
     *  @throws CommandException  If command can't be executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws CommandException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ATTRIBUTE_USER);
        Spetification spetification = new Spetification();
        spetification.setName(request.getParameter(ATTRIBUTE_SP_NAME));
        spetification.setJobs(Integer.parseInt(request.getParameter(ATTRIBUTE_SP_JOBS)));
        spetification.setId(Integer.parseInt(request.getParameter(ATTRIBUTE_SP_ID)));

        SpecificationDAOImpl specificationDAO = SpecificationDAOImpl.getInstance();

        try {
            specificationDAO.updateSpetificationName(spetification.getName(), spetification.getId());
            specificationDAO.updateSpetificationJobs(spetification.getJobs(), spetification.getId());
        }
        catch (DAOException e){
            throw new CommandException(ResourceManager.getProperty(MSG_EXECUTE_ERROR) + user.getId(), e);
        }
        logger.info(ResourceManager.getProperty(MSG_REQUESTED_COMMAND) + user.getId());
        return EDIT_ORDER_PAGE;
    }
}
