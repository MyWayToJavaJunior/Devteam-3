package com.epam.task6.command;


import com.epam.task6.command.impl.NoFound;
import com.epam.task6.command.impl.ShowDeveloperPage;
import com.epam.task6.command.impl.authorize.Login;
import com.epam.task6.command.impl.authorize.Logout;
import com.epam.task6.command.impl.content.*;
import com.epam.task6.command.impl.handle.*;
import com.epam.task6.command.impl.language.ChangeLanguage;
import com.epam.task6.resource.ResourceManager;
import com.epam.task6.service.checker.AccessChecker;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public final class CommandHelper {
    /* Initializing command activity */
    private static Logger logger = Logger.getLogger("activity");

    /* Keeps name of parameter which contains command name */
    private static final String EXECUTION_COMMAND = "executionCommand";

    /* Keeps illegal command message */
    private static final String LOGGER_ILLEGAL_COMMAND = "logger.error.illegal.command";


    /**
     * This method defines command
     *
     * @param request HttpServletRequest object
     * @return Command object
     */
    public static Command getCommand(HttpServletRequest request){
        Command command = null;
        CommandName commandType = getCommandEnum(request.getParameter(EXECUTION_COMMAND));
       if (AccessChecker.checkPermission(request, commandType))
        {
            switch (commandType){
                case LOGIN:
                    command = new Login();
                    break;
                case LOGOUT:
                    command = new Logout();
                    break;
                case SHOW_SPECIFICATIONS:
                    command = new ViewSpecifications();
                    break;
                case SHOW_ORDER_FORM:
                    command = new ViewOrderForm();
                    break;
                case DEV:
                    command = new ShowDeveloperPage();
                    break;
                case SHOW_PROJECTS:
                    command = new ViewManagerProject();
                    break;
                case SHOW_BILLS:
                    command = new ViewBills();
                    break;
                case CREATE_ORDER_PART_ONE:
                    command = new CreateOrderPartOne();
                    break;
                case  CREATE_ORDER_PART_TWO:
                    command = new CreateOrderPartTwo();
                    break;
                case SHOW_NEW_PROJECTS:
                    command = new ViewNewProject();
                    break;
                case SHOW_CURRENT_PROJECTS:
                    command = new ViewCurrentProject();
                    break;
                case SHOW_READY_PROJECTS:
                    command = new ViewReadyProject();
                    break;
                case TRANSFER_NEW_PROJECT:
                    command = new TrasferProjectToCurrent();
                    break;
                case TRANSFER_CURRENT_PROJECT:
                    command = new TrasferProjectToReady();
                    break;
                case SHOW_ASSIGN_PROJECT_FORM:
                    command = new ViewAssignProjectForm();
                    break;
                case ASSIGN_PROJECT:
                    command = new AssignProject();
                    break;
                case SHOW_PROJECT_FORM:
                    command = new ViewProjectForm();
                    break;
                case SHOW_PROJECT_FORM_DETAILS:
                    command = new ViewProjectFormDetails();
                    break;
                case CHANGE_LANGUAGE:
                    command = new ChangeLanguage();
                    break;
                case EDIT_ORDER:
                    command = new ViewEditOrder();
                    break;
                case EDIT_ORDER_DETAIL:
                    command = new EditOrder();
                    break;
                case SHOW_CUSTOMER_JOBS:
                    command = new ViewJobsBySpetification();
                    break;
                case VIEW_EDIT_PROJECT:
                    command = new ViewEditProject();
                    break;
                case DELETE_PROJECT:
                    command = new DeleteProject();
                    break;
                case EDIT_PRIJECT:
                    command = new EditProject();
                    break;
                case VIEW_WAITING_ORDER:
                    command = new ViewWaitingOrder();
                    break;
                case CREATE_PROJECT:
                    command = new ViewProject();
                    break;
                default:
                    command = new NoFound();
                    break;
           }
        }
        else {
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
            logger.error(ResourceManager.getProperty(LOGGER_ILLEGAL_COMMAND), exception);
            commandEnum = CommandName.NO_SUCH_COMMAND;
        }
        return commandEnum;
    }

}