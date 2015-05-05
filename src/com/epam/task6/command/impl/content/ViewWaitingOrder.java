package com.epam.task6.command.impl.content;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.SpecificationDAO;
import com.epam.task6.domain.project.Spetification;
import com.epam.task6.resource.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by olga on 05.05.15.
 */
public class ViewWaitingOrder extends Command
{
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.specifications";
    private static final String MSG_REQUESTED = "logger.activity.requested.specifications";

    /* Attributes and parameters */
    private static final String LIST_OF_SPECIFICATIONS = "specificationsList";
    private static final String USER_ATTRIBUTE = "user";
    private static final String PAGE = "jsp/manager/watingOrders.jsp";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, DAOException {

        SpecificationDAO specificationDAO = SpecificationDAO.getInstance();
        try {
            List<Spetification> spetificationList = specificationDAO.getUserSpecificationsByStatus(0);
            if (null != spetificationList) {
                request.setAttribute("spetification", spetificationList);
            }
        }
        catch (DAOException e){
            throw new CommandException(ResourceManager.getProperty(MSG_EXECUTE_ERROR), e);
        }
        //    logger.info(ResourceManager.getProperty(MSG_REQUESTED) + user.getId());
        setForward(PAGE);
    }
}
