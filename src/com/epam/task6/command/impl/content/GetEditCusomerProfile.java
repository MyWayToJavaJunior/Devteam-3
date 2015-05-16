package com.epam.task6.command.impl.content;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by olga on 16.05.15.
 */
public class GetEditCusomerProfile extends Command {
    private static GetEditCusomerProfile instance = new GetEditCusomerProfile();

    public static GetEditCusomerProfile getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        return "jsp/customer/editCustomerProfile.jsp";
    }
}
