package com.epam.task6.command.impl.authorize;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.dao.impl.RegisterDAOImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register extends Command {
    private static Register instance = new Register();

    public static Register getInstance() {
        return instance;
    }

    @Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		String page = null;
		
		String email = request.getParameter(RequestParameterName.EMAIL);
		String password = request.getParameter(RequestParameterName.PASSWORD);
		String firstName = request.getParameter(RequestParameterName.FIRSTNAME);
        String secondName = request.getParameter(RequestParameterName.SECONDNAME);

        RegisterDAOImpl registerDAO = new RegisterDAOImpl();
        registerDAO.registerUser(email, password, firstName, secondName);
        return null;

	}
}
