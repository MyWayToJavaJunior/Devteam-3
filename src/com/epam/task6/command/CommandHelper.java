package com.epam.task6.command;


import com.epam.task6.command.impl.ShowDeveloperPage;
import com.epam.task6.command.impl.ShowManagerPage;
import com.epam.task6.command.impl.authorize.Login;
import com.epam.task6.command.impl.authorize.Logout;
import com.epam.task6.command.impl.content.*;
import com.epam.task6.command.impl.handle.*;
import com.epam.task6.command.impl.language.ChangeLanguage;
import com.epam.task6.service.checker.AccessChecker;

import javax.servlet.http.HttpServletRequest;

public final class CommandHelper {

    private static final String EXECUTION_COMMAND = "executionCommand";


    public static Command getCommand(HttpServletRequest request){
        Command command = null;
        CommandName commandType = getCommandEnum(request.getParameter(EXECUTION_COMMAND));

        System.out.println(" EX   COMMAND  " + request.getParameter(EXECUTION_COMMAND));
       if (AccessChecker.checkPermission(request, commandType))
        {
            System.out.println("111111");
            switch (commandType){
                case LOGIN:
                    System.out.println("222222");
                    command = new Login();
                    break;
                case LOGOUT:
                    command = new Logout();
                    break;
                case SHOW_MENU_COMMAND:
                    command = new ShowManagerPage();
                    break;
                case SHOW_SPECIFICATIONS:
                    command = new ShowSpecifications();
                    break;
                case SHOW_ORDER_FORM:
                    command = new ShowOrderForm();
                    break;
                case DEV:
                    command = new ShowDeveloperPage();
                    break;
                case SHOW_PROJECTS:
                    command = new ShowProjects();
                    break;
                case SHOW_BILLS:
                    command = new ShowBills();
                    break;
                case CREATE_ORDER_PART_ONE:
                    command = new CreateOrderPartOne();
                    break;
                case  CREATE_ORDER_PART_TWO:
                    command = new CreateOrderPartTwo();
                    break;
                case SHOW_NEW_PROJECTS:
                    command = new ShowNewProject();
                    break;
                case SHOW_CURRENT_PROJECTS:
                    command = new ShowCurrentProject();
                    break;
                case SHOW_READY_PROJECTS:
                    command = new ShowReadyProject();
                    break;
                case TRANSFER_NEW_PROJECT:
                    command = new TrasferProjectToCurrent();
                    break;
                case TRANSFER_CURRENT_PROJECT:
                    command = new TrasferProjectToReady();
                    break;
                case SHOW_ASSIGN_PROJECT_FORM:
                    command = new ShowAssignProjectForm();
                    break;
                case ASSIGN_PROJECT:
                    command = new AssignProject();
                    break;
                case SHOW_PROJECT_FORM:
                    command = new ShowProjectForm();
                    break;
                case SHOW_PROJECT_FORM_DETAILS:
                    command = new ShowProjectFormDetails();
                    break;
                case CHANGE_LANGUAGE:
                    command = new ChangeLanguage();
                    break;
                case EDIT_ORDER:
                    command = new ShowEditOrder();
                    break;
                case EDIT_ORDER_DETAIL:
                    command = new EditOrder();
                    break;
                case SHOW_CUSTOMER_JOBS:
                    command = new ViewJobsBySpetification();
                    break;

           }
        }
        else {
           System.out.print("else11111");
           command = new Login();

       }
        return command;
    }

    /**
     * This method initializing enum from command name
     *
     * @param executionCommand Name of command
     * @return Command enum
     */
    private static CommandName getCommandEnum(String executionCommand) {
        CommandName commandEnum = null;
        try {
            commandEnum = CommandName.valueOf(executionCommand);
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();

            commandEnum = CommandName.NO_SUCH_COMMAND;
        }
        return commandEnum;
    }

}