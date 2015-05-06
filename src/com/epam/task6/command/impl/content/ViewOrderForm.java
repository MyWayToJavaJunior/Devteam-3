package com.epam.task6.command.impl.content;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.QualificationDAO;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by olga on 26.04.15.
 */
public class ViewOrderForm extends Command {
    /* Initializes activity logger */
    private static Logger logger = Logger.getLogger(ViewOrderForm.class);

    /* Logger messages */
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.order.form";
    private static final String MSG_REQUESTED_COMMAND = "logger.activity.requested.order.form";

    /* Attributes and parameters */
    private static final String PARAM_QUALIFICATIONS = "qualifications";
    private static final String USER_ATTRIBUTE = "user";

    /* Forward page */
    private static final String FORWARD_ORDER_FORM = "jsp/customer/addSpetification.jsp";

    /**
     * Implementation of command
     *
     * @param request HttpServletRequest object
     * @return page or forward command.
     * @throws CommandException If an error has occurred on runtime
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = (User)request.getSession().getAttribute(USER_ATTRIBUTE);
        QualificationDAO dao = QualificationDAO.getInstance();
        try {
            request.setAttribute(PARAM_QUALIFICATIONS, dao.getAllQualifications());
        }   catch (DAOException e) {
            logger.error(ResourceManager.getProperty(MSG_EXECUTE_ERROR)+ user.getId(), e);
        }
        logger.info(ResourceManager.getProperty(MSG_REQUESTED_COMMAND) + user.getId());
        setForward(FORWARD_ORDER_FORM);
    }

}
