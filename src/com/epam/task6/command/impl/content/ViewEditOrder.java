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

/**
 * Created by olga on 29.04.15.
 */
public class ViewEditOrder extends Command {

    private static Logger logger = Logger.getLogger(ViewEditOrder.class);

    private static final String MSG_SHOW_BILLS = "logger.activity.customer.show.bills";
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.view.edit.order";

    private static final String CUSTOMER_EDIT_SPETIFICATION_PAGE = "jsp/customer/editSpetification.jsp";

    private static final String LIST_OF_SPETIFICATION = "editSp";
    private static final String SPETIFICATION = "spId";
    private static final String USER_ATTRIBUTE = "user";

    /**
     * This method executes the command.
     *
     * @param request HttpServletRequest object
     * @return page or forward command.
     * @throws CommandException  If command can't be executed.
     * @throws DAOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        User user = (User)request.getSession().getAttribute(USER_ATTRIBUTE);
        int spetificationId = Integer.parseInt(request.getParameter(SPETIFICATION));

        try {
            SpecificationDAO specificationDAO = SpecificationDAO.getInstance();
            Spetification spetification = specificationDAO.getSpetificationById(spetificationId);

            if (spetification != null) {
                request.setAttribute(LIST_OF_SPETIFICATION, spetification);
            }
        }
        catch (DAOException e){
            logger.error(ResourceManager.getProperty(MSG_EXECUTE_ERROR)+ user.getId(), e);
        }
        logger.info(ResourceManager.getProperty(MSG_SHOW_BILLS) + user.getId());
        setForward(CUSTOMER_EDIT_SPETIFICATION_PAGE);
    }
}
