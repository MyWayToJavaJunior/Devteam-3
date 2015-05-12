package com.epam.task6.command;


import com.epam.task6.command.impl.content.GetDeveloperPage;
import com.epam.task6.command.impl.navigate.NoFound;
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
                    command = Login.getInstance();
                    break;
                case LOGOUT:
                    command = Logout.getInstance();
                    break;
                case SHOW_SPECIFICATIONS:
                    command = GetSpecifications.getInstance();
                    break;
                case SHOW_ORDER_FORM:
                    command = GetOrderForm.getInstance();
                    break;
                case DEV:
                    command = GetDeveloperPage.getInstance();
                    break;
                case SHOW_PROJECTS:
                    command = GetManagerProject.getInstance();
                    break;
                case SHOW_BILLS:
                    command = GetBills.getInstance();
                    break;
                case CREATE_ORDER:
                    command = CreateOrder.getInstance();
                    break;
                case CREATE_JOB:
                    command = CreateJob.getInstance();
                    break;
                case SHOW_NEW_PROJECTS:
                    command = GetNewProject.getInstance();
                    break;
                case SHOW_CURRENT_PROJECTS:
                    command = GetCurrentProject.getInstance();
                    break;
                case SHOW_READY_PROJECTS:
                    command = GetReadyProject.getInstance();
                    break;
                case TRANSFER_NEW_PROJECT:
                    command = TrasferProjectToCurrent.getInstance();
                    break;
                case TRANSFER_CURRENT_PROJECT:
                    command = TrasferProjectToReady.getInstance();
                    break;
                case SHOW_ASSIGN_PROJECT_FORM:
                    command = GetAssignProjectForm.getInstance();
                    break;
                case ASSIGN_PROJECT:
                    command = AssignProject.getInstance();
                    break;
                case SHOW_PROJECT_FORM:
                    command = GetProjectForm.getInstance();
                    break;
                case SHOW_PROJECT_FORM_DETAILS:
                    command = GetProjectFormDetails.getInstance();
                    break;
                case CHANGE_LANGUAGE:
                    command = ChangeLanguage.getInstance();
                    break;
                case EDIT_ORDER:
                    command = GetEditOrder.getInstance();
                    break;
                case EDIT_ORDER_DETAIL:
                    command = EditOrder.getInstance();
                    break;
                case SHOW_CUSTOMER_JOBS:
                    command = GetJobsBySpetification.getInstance();
                    break;
                case VIEW_EDIT_PROJECT:
                    command = GetEditProject.getInstance();
                    break;
                case DELETE_PROJECT:
                    command = DeleteProject.getInstance();
                    break;
                case EDIT_PRIJECT:
                    command = EditProject.getInstance();
                    break;
                case VIEW_WAITING_ORDER:
                    command = GetWaitingOrder.getInstance();
                    break;
                case CREATE_PROJECT:
                    command = GetProject.getInstance();
                    break;
                case VIEW_USER:
                    command = GetUserProfile.getInstance();
                    break;
                default:
                    command = NoFound.getInstance();
                    break;
           }
        }
        else {
           command = Login.getInstance();

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