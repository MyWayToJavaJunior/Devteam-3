package com.epam.task6.controller;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.command.CommandHelper;
import com.epam.task6.dao.DAOException;
import com.epam.task6.resource.ResourceManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    /* Logger messages */
    private static final String MSG_EXECUTE_ERROR = "logger.error.execute.command";

    /* Forward page */
    private static final String FORWARD_SERVER_ERROR = "forward.error.500";
	private static final long serialVersionUID = 1L;

    public Controller() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
	}

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter(RequestParameterName.COMMAND_NAME));
        Command command = CommandHelper.getCommand(request);
        try {
            command.execute(request,response);
            request.getRequestDispatcher(command.getForward()).forward(request, response);
        } catch (CommandException e) {
            command.setForward(ResourceManager.getProperty(FORWARD_SERVER_ERROR));
            e.printStackTrace();
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
