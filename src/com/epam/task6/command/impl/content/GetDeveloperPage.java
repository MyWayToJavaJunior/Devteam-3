package com.epam.task6.command.impl.content;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.domain.user.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This command get developer page.
 *
 * Created by olga on 21.04.15.
 */
public class GetDeveloperPage extends Command {
    private static GetDeveloperPage instance = new GetDeveloperPage();

    private static final Logger logger = Logger.getLogger(GetDeveloperPage.class);

    /** Logger messages */
    private static final String MSG_REQUESTED = "";

    /** Attributes and parameters */
    private static final String ATTRIBUTE_USER = "user";

    /** Forward pages */
    public static final String DEVELOPER_PAGE = "jsp/developer/mainDeveloper.jsp";


    public static GetDeveloperPage getInstance() {
        return instance;
    }

    /**
     *  Implementation of command that get developer page.
     *
     *  @param request HttpServletRequest object
     *  @param response HttpServletResponse object
     *  @throws CommandException  If command can't be executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = (User)request.getSession().getAttribute(ATTRIBUTE_USER);
        logger.info((MSG_REQUESTED) + user.getId());
        return DEVELOPER_PAGE;
    }
}
