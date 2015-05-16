package com.epam.task6.command.impl.content;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.UserDAOImpl;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by olga on 06.05.15.
 */
public class GetUserProfile extends Command {
    private static GetUserProfile instance = new GetUserProfile();
    private static Logger logger = Logger.getLogger(GetSpecifications.class);

    /* Logger messages */
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.specifications";
    private static final String MSG_REQUESTED = "logger.error.execute.view.project";

    /* Attributes and parameters */
    private static final String LIST_OF_USER = "userList";
    private static final String USER_ATTRIBUTE = "user";
    private static final String SHOW_USER_PAGE = "jsp/manager/user/mainUserAction.jsp";

    public static GetUserProfile getInstance() {
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
        UserDAOImpl userDAO = UserDAOImpl.getInstance();
        try {
            int user1 = userDAO.getUserByName("olga");

        }
        catch (DAOException e){
            throw new CommandException(ResourceManager.getProperty(MSG_EXECUTE_ERROR) + user.getId(), e);
        }
        logger.info(ResourceManager.getProperty(MSG_REQUESTED) + user.getId());

        return (SHOW_USER_PAGE);
    }
}
