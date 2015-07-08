package com.epam.task6.command.impl.content;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.SpecificationDAOImpl;
import com.epam.task6.domain.project.Spetification;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * his command get page with spetification.
 *
 * Created by olga on 19.04.15.
 */
public class GetSpecifications extends Command {

    private static GetSpecifications instance = new GetSpecifications();
    private static final Logger logger = Logger.getLogger(GetSpecifications.class);

    /** Logger messages */
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.specifications";
    private static final String MSG_REQUESTED = "logger.error.execute.view.project";

    /** Attributes and parameters */
    private static final String USER_ATTRIBUTE = "user";

    /** Forward page */
    private static final String SPECIFICATIONS_PAGE = "jsp/customer/showSpetification.jsp";


    public static GetSpecifications getInstance() {
        return instance;
    }

    /**
     * Implementation of command that get page with spetification.
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @return rederict page or command.
     * @throws CommandException if an error has occurred on runtime
     */
   @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

       User user = (User)request.getSession().getAttribute(USER_ATTRIBUTE);
       SpecificationDAOImpl specificationDAO = SpecificationDAOImpl.getInstance();
       try {
           List<Spetification> spetificationList = specificationDAO.getUserSpecifications(user.getId());
           if (null != spetificationList) {
               request.setAttribute(RequestParameterName.SIMPLE_INFO, spetificationList);
           }
       }
       catch (DAOException e){
           throw new CommandException(ResourceManager.getProperty(MSG_EXECUTE_ERROR) + user.getId(), e);
       }
       logger.info(ResourceManager.getProperty(MSG_REQUESTED) + user.getId());
       return SPECIFICATIONS_PAGE;
   }
}

