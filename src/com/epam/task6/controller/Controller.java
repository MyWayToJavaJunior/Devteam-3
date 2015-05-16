package com.epam.task6.controller;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.command.CommandHelper;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {

    /* Initializes error logger */
    private static final Logger logger = Logger.getLogger(Controller.class);

    /* Logger messages */
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.command";

    /* Forward page */
    private static final String FORWARD_SERVER_ERROR = "forward.error.500";

	private static final long serialVersionUID = 1L;

    public Controller() {
        super();
    }

    /**
     * Overrides method which handling requests with method get
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException object
     * @throws IOException object
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            processRequest(request, response);
    }

    /**
     * Overrides method which handling requests with method post
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException object
     * @throws IOException object
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException object
     * @throws IOException object
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = CommandHelper.getCommand(request);
        String page;
        try {
           page = command.execute(request,response);

        } catch (CommandException e) {
            page = FORWARD_SERVER_ERROR;
            logger.error(ResourceManager.getProperty(MSG_EXECUTE_ERROR), e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        //dispatcher может быть 0
        if (dispatcher != null){
            dispatcher.forward(request, response);
        }
        else {
            logger.error(ResourceManager.getProperty(MSG_EXECUTE_ERROR));
        }
    }
}
