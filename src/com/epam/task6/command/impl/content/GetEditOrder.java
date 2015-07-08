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

/**
 * This command get page for edit spetification.
 *
 * Created by olga on 29.04.15.
 */
public class GetEditOrder extends Command {
    private static GetEditOrder instance = new GetEditOrder();

    private static final Logger logger = Logger.getLogger(GetEditOrder.class);

    /** Logger messages */
    private static final String MSG_SHOW_EDIT_ORDER = "logger.activity.customer.show.edit.order";
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.view.edit.order";

    /** Forward pages */
    private static final String CUSTOMER_EDIT_SPETIFICATION_PAGE = "jsp/customer/editSpetification.jsp";

    /** Attributes and parameters */
    private static final String LIST_OF_SPETIFICATION = "editSp";
    private static final String SPETIFICATION = "spId";
    private static final String USER_ATTRIBUTE = "user";

    public static GetEditOrder getInstance() {
        return instance;
    }

    /**
     * Implementation of command get page for edit spetification.
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws CommandException If execution is failed
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        User user = (User)request.getSession().getAttribute(USER_ATTRIBUTE);
        int spetificationId = Integer.parseInt(request.getParameter(SPETIFICATION));

        try {
            SpecificationDAOImpl specificationDAO = SpecificationDAOImpl.getInstance();
            Spetification spetification = specificationDAO.getSpetificationById(spetificationId);

            if (spetification != null) {
                request.setAttribute(LIST_OF_SPETIFICATION, spetification);
            }
        }
        catch (DAOException e){
            throw new CommandException(ResourceManager.getProperty(MSG_EXECUTE_ERROR)+ user.getId(), e);
        }
        logger.info(ResourceManager.getProperty(MSG_SHOW_EDIT_ORDER) + user.getId());
        return CUSTOMER_EDIT_SPETIFICATION_PAGE;
    }
}
