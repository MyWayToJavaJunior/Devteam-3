package com.epam.task6.service.handler;

import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.JobDAOImpl;
import com.epam.task6.dao.impl.ProjectDAOImpl;
import com.epam.task6.dao.impl.UserDAOImpl;
import com.epam.task6.domain.verifable.PreparedJob;
import com.epam.task6.domain.verifable.PreparedProject;
import com.epam.task6.resource.ResourceManager;
import com.epam.task6.service.ServiceException;

import java.util.ArrayList;

/**
 * Created by olga on 30.04.15.
 */
public class ProjectHandler {
    /** Logger messages */
    private static final String ERROR_SAVE_PROJECT = "logger.logic.error.save.project";

    public static void saveProject(PreparedProject project, int mid, int did, int status) throws ServiceException {
        ProjectDAOImpl projectDAO = new ProjectDAOImpl();
        JobDAOImpl jobDAO = new JobDAOImpl();
        UserDAOImpl userDAO = new UserDAOImpl();
        ArrayList<PreparedJob> jobs = project.getJobs();
        try {
            for (PreparedJob job : jobs) {
                jobDAO.setJobCost(job.getId(), job.getCost());
                ArrayList<String> mails = job.getEmployees();
                for (String mail : mails) {
                  userDAO.takeEmployee(job.getId(), mail.replace("%40","@"));
                }
            }
            projectDAO.saveProject(project.getName(), mid, project.getId(), did, status);
        } catch (DAOException e) {
            throw new ServiceException(ResourceManager.getProperty(ERROR_SAVE_PROJECT) + project.getId(), e);
        }
    }

}
