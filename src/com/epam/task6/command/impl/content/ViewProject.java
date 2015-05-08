package com.epam.task6.command.impl.content;

import com.epam.task6.command.Command;
import com.epam.task6.command.CommandException;
import com.epam.task6.domain.project.Spetification;
import com.epam.task6.domain.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by olga on 01.05.15.
 */
public class ViewProject extends Command {
    private static ViewProject instance = new ViewProject();

    private static final String ATTRIBUTE_USER = "user";
    private static final String ATTRIBUTE_SHOW_PROJECT_PAGE = "Controller?executionCommand=SHOW_PROJECTS";
    private static final String ATTRIBUTE_SPETIFICATION = "spec";

    public static ViewProject getInstance() {
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
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ATTRIBUTE_USER);
        Spetification spetification = (Spetification) session.getAttribute(ATTRIBUTE_SPETIFICATION);
        setForward(ATTRIBUTE_SHOW_PROJECT_PAGE);
    }
}
