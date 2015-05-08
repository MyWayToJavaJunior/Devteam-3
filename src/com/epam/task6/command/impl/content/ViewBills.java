package com.epam.task6.command.impl.content;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.BillDAO;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This command collects all bills for user and forward user to bill page.
 *
 * Created by olga on 28.04.15.
 */
public class ViewBills extends Command {
    private static ViewBills instance = new ViewBills();
    private static Logger logger = Logger.getLogger(ViewBills.class);

    private static final String MSG_SHOW_BILLS = "logger.activity.customer.show.bills";
    private static final String MSG_ERROR_LOADING_BILLS = "logger.error.show.bills";

    private static final String CUSTOMER_BILLS_PAGE = "jsp/customer/bills.jsp";

    private static final String LIST_OF_BILLS = "billsList";
    private static final String USER_ATTRIBUTE = "user";


    public static ViewBills getInstance() {
        return instance;
    }

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
        BillDAO dao = BillDAO.getInstance();
        try {
            request.setAttribute(LIST_OF_BILLS, dao.getCustomerBills(user.getId()));
        } catch (DAOException e) {
            throw new CommandException(ResourceManager.getProperty(MSG_ERROR_LOADING_BILLS) + user.getId(), e);
        }
        logger.info(ResourceManager.getProperty(MSG_SHOW_BILLS) + user.getId());
        setForward(CUSTOMER_BILLS_PAGE);
    }
}
