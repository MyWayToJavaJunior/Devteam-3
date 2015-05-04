package com.epam.task6.command.impl;

import com.epam.task6.controller.JspPageName;
import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowManagerPage extends Command {

		@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
          setForward( JspPageName.MANAGER_PAGE);
    }


}
