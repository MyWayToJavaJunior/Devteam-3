package com.epam.task6.command.impl.content;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.QualificationDAOImpl;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by olga on 26.04.15.
 */
public class GetOrderForm extends Command {
    private static GetOrderForm instance = new GetOrderForm();
    /* Initializes activity logger */
    private static Logger logger = Logger.getLogger(GetOrderForm.class);

    /* Logger messages */
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.order.form";
    private static final String MSG_REQUESTED_COMMAND = "logger.activity.requested.order.form";

    /* Attributes and parameters */
    private static final String PARAM_QUALIFICATIONS = "qualifications";
    private static final String USER_ATTRIBUTE = "user";

    /* Forward page */
    private static final String FORWARD_ORDER_FORM = "jsp/customer/addSpetification.jsp";

    public static GetOrderForm getInstance() {
        return instance;
    }

    /**
     * This method invalidates user session
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws CommandException If execution is failed
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = (User)request.getSession().getAttribute(USER_ATTRIBUTE);
        QualificationDAOImpl dao = QualificationDAOImpl.getInstance();
        try {
            request.setAttribute(PARAM_QUALIFICATIONS, dao.getAllQualifications());
        }   catch (DAOException e) {
            throw new CommandException(ResourceManager.getProperty(MSG_EXECUTE_ERROR)+ user.getId(), e);
        }
        logger.info(ResourceManager.getProperty(MSG_REQUESTED_COMMAND) + user.getId());
        return (FORWARD_ORDER_FORM);
    }

}
