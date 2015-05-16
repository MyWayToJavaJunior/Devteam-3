package com.epam.task6.command.impl.content;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.controller.JspPageName;
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
    private static final Logger logger = Logger.getLogger("activity");

    /** Logger messages */
    private static final String MSG_REQUESTED = "";
    private static final String MSG_EXECUTE_ERROR = "";

    /** Attributes and parameters */
    private static final String ATTRIBUTE_USER = "user";
    private static final String ATTRIBUTE_FORM_CORRECT = " ";
    private static final String PARAM_FORWARD_PAGE = " ";

    public static GetDeveloperPage getInstance() {
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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        return (JspPageName.DEVELOPER_PAGE);
    }
}
