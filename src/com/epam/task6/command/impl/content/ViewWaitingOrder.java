package com.epam.task6.command.impl.content;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.SpecificationDAO;
import com.epam.task6.domain.project.Spetification;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Implementing command pattern.
 *
 * Created by olga on 05.05.15.
 */
public class ViewWaitingOrder extends Command
{
    private static Logger logger = Logger.getLogger(ViewWaitingOrder.class);
    private static final String MSG_EXECUTE_ERROR = "logger.db.error.get.waiting.specifications";
    private static final String MSG_REQUESTED = "logger.activity.requested.waiting.orders";

    /* Attributes and parameters */
    private static final String LIST_OF_SPECIFICATIONS = "spetification";
    private static final String USER_ATTRIBUTE = "user";
    private static final String PAGE = "jsp/manager/watingOrders.jsp";

    /**
     * Implementation of command
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws CommandException If an error has occurred on runtime
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        User user = (User)request.getSession().getAttribute(USER_ATTRIBUTE);
        SpecificationDAO specificationDAO = SpecificationDAO.getInstance();
        try {
            List<Spetification> spetificationList = specificationDAO.getUserSpecificationsByStatus(0);
            if (null != spetificationList) {
                request.setAttribute(LIST_OF_SPECIFICATIONS, spetificationList);
            }
        }
        catch (DAOException e){
            throw new CommandException(ResourceManager.getProperty(MSG_EXECUTE_ERROR), e);
        }
           logger.info(ResourceManager.getProperty(MSG_REQUESTED) + user.getId());
        setForward(PAGE);
    }
}
