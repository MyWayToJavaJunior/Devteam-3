package com.epam.task6.command.impl.content;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.domain.user.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This command get page for add job.
 *
 * Created by olga on 16.05.15.
 */
public class GetJob extends Command {
    private static GetJob instance = new GetJob();
    private static final Logger logger = Logger.getLogger(GetJob.class);

    /** Logger messages */
    private static final String MSG_REQUESTED = "";

    /** Attributes and parameters */
    private static final String ATTRIBUTE_USER = "user";

    /** Forward pages */
    public static final String ADD_JOB_PAGE = "jsp/customer/addjobs.jsp";

    public static GetJob getInstance() {
        return instance;
    }

    /**
     * Implementation of command that get page for add job.
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @return page to rederict
     * @throws CommandException
     */

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = (User)request.getSession().getAttribute(ATTRIBUTE_USER);
        logger.info((MSG_REQUESTED) + user.getId());
        return ADD_JOB_PAGE;
    }
}
