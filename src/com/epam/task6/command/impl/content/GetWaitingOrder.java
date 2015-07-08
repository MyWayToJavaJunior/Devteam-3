package com.epam.task6.command.impl.content;

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
import java.util.List;

/**
 * This command get wating orders page
 *
 * Created by olga on 05.05.15.
 */
public class GetWaitingOrder extends Command
{
    private static GetWaitingOrder instance = new GetWaitingOrder();
    private static final Logger logger = Logger.getLogger(GetWaitingOrder.class);

    /** Logger messages */
    private static final String MSG_EXECUTE_ERROR = "logger.db.error.get.waiting.specifications";
    private static final String MSG_REQUESTED = "logger.activity.requested.waiting.orders";

    /** Attributes and parameters */
    private static final String LIST_OF_SPECIFICATIONS = "spetification";
    private static final String USER_ATTRIBUTE = "user";

    /** Forward page */
    private static final String WAITING_ORDERS_PAGE = "jsp/manager/watingOrders.jsp";

    public static GetWaitingOrder getInstance() {
        return instance;
    }

    /**
     * Implementation of command that get wating orders page
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @return rederict page or command
     * @throws CommandException If an error has occurred on runtime
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        User user = (User)request.getSession().getAttribute(USER_ATTRIBUTE);
        SpecificationDAOImpl specificationDAO = SpecificationDAOImpl.getInstance();
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
        return WAITING_ORDERS_PAGE;
    }
}
