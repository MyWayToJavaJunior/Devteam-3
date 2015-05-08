package com.epam.task6.command.impl.handle;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.SpecificationDAO;
import com.epam.task6.domain.project.Spetification;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by olga on 04.05.15.
 */
public class EditOrder extends Command {

    private static EditOrder instance = new EditOrder();
    /** Initialize activity logger */
    private static Logger logger = Logger.getLogger(DeleteProject.class);

    /** Logger messages */
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.create.order";

    /** Attributes and parameters */
    private static final String ATTRIBUTE_USER = "user";
    private static final String ATTRIBUTE_SP_NAME = "order";
    private static final String ATTRIBUTE_SP_JOBS= "job";
    private static final String ATTRIBUTE_SP_ID = "id";
    private static final String EDIT_ORDER_PAGE = "Controller?executionCommand=SHOW_SPECIFICATIONS";

    public static EditOrder getInstance() {
        return instance;
    }

    /**
     *  This method executes the command.
     *
     *  @param request HttpServletRequest object
     *  @param response HttpServletResponse object
     *  @throws CommandException  If command can't be executed.
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws CommandException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ATTRIBUTE_USER);
        Spetification spetification = new Spetification();
        spetification.setName(request.getParameter(ATTRIBUTE_SP_NAME));
        spetification.setJobs(Integer.parseInt(request.getParameter(ATTRIBUTE_SP_JOBS)));
        spetification.setId(Integer.parseInt(request.getParameter(ATTRIBUTE_SP_ID)));

        SpecificationDAO specificationDAO = SpecificationDAO.getInstance();

        try {
            specificationDAO.updateSpetificationName(spetification.getName(), spetification.getId());
            specificationDAO.updateSpetificationJobs(spetification.getJobs(), spetification.getId());
        }
        catch (DAOException e){
            throw new CommandException(ResourceManager.getProperty(MSG_EXECUTE_ERROR) + user.getId(), e);
        }

        setForward(EDIT_ORDER_PAGE);
    }
}
