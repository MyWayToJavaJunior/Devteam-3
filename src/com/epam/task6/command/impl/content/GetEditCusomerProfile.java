package com.epam.task6.command.impl.content;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.domain.user.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This command get edit profile page.
 *
 * Created by olga on 16.05.15.
 */
public class GetEditCusomerProfile extends Command {

    private static GetEditCusomerProfile instance = new GetEditCusomerProfile();

    private static final Logger logger = Logger.getLogger(GetEditCusomerProfile.class);

    /** Logger messages */
    private static final String MSG_REQUESTED = "";

    /** Attributes and parameters */
    private static final String ATTRIBUTE_USER = "user";

    /** Forward pages */
    private static final String EDIT_PROFILE_PAGE = "jsp/customer/editCustomerProfile.jsp";


    public static GetEditCusomerProfile getInstance() {
        return instance;
    }

    /**
     * Implementation of command that
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @return rederict page or command
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = (User)request.getSession().getAttribute(ATTRIBUTE_USER);
        logger.info(MSG_REQUESTED + user.getId());
        return EDIT_PROFILE_PAGE;
    }
}
