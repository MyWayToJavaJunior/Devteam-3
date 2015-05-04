package com.epam.task6.command.impl.authorize;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.RegisterDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register extends Command {

@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, DAOException {

		String page = null;
		
		String email = request.getParameter(RequestParameterName.EMAIL);
		String password = request.getParameter(RequestParameterName.PASSWORD);
		String firstName = request.getParameter(RequestParameterName.FIRSTNAME);
        String secondName = request.getParameter(RequestParameterName.SECONDNAME);

        RegisterDAO registerDAO = new RegisterDAO();
        registerDAO.registerUser(email, password, firstName, secondName);

	}
}
