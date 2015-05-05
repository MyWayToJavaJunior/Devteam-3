package com.epam.task6.command.impl.content;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.SpecificationDAO;
import com.epam.task6.domain.project.Spetification;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by olga on 19.04.15.
 */
public class ViewSpecifications extends Command {


  //  private static Logger logger = Logger.getLogger("activity");

    /* Logger messages */
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.specifications";
    private static final String MSG_REQUESTED = "logger.activity.requested.specifications";

    /* Attributes and parameters */
    private static final String LIST_OF_SPECIFICATIONS = "specificationsList";
    private static final String USER_ATTRIBUTE = "user";
    private static final String SPECIFICATIONS_PAGE = "jsp/customer/showSpetification.jsp";



    /**
     * This method executes the command.
     *
     * @param request HttpServletRequest object
     * @return page or forward command.
     * @throws CommandException  If command can't be executed.
     * @throws DAOException
     */
   @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, DAOException {

       User user = (User)request.getSession().getAttribute(USER_ATTRIBUTE);
       SpecificationDAO specificationDAO = SpecificationDAO.getInstance();
       try {
           List<Spetification> spetificationList = specificationDAO.getUserSpecifications(user.getId());
           if (null != spetificationList) {
               request.setAttribute(RequestParameterName.SIMPLE_INFO, spetificationList);
           }
       }
       catch (DAOException e){
           throw new CommandException(ResourceManager.getProperty(MSG_EXECUTE_ERROR) + user.getId(), e);
       }
   //    logger.info(ResourceManager.getProperty(MSG_REQUESTED) + user.getId());
       setForward(SPECIFICATIONS_PAGE);
   }
}

