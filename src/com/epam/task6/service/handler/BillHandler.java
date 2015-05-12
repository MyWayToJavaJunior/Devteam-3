package com.epam.task6.service.handler;

import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.BillDAOImpl;
import com.epam.task6.dao.impl.JobDAOImpl;
import com.epam.task6.dao.impl.ProjectDAOImpl;
import com.epam.task6.dao.impl.SpecificationDAOImpl;
import com.epam.task6.domain.project.Project;
import com.epam.task6.resource.ResourceManager;
import com.epam.task6.service.ServiceException;
import com.epam.task6.service.builder.BillNameBuilder;

/**
 * Created by olga on 30.04.15.
 */
public class BillHandler {
    /** Logger messages */
    private static final String ERROR_SAVE_BILL = "logger.logic.error.save.bill";

    /**
     * This method saves new bill to database. <br />
     * Must be executed only if was successfully created project by customer.
     *
     * @param spetificationId Specification id
     * @throws ServiceException object if creation is failed
     */
    public static void saveBill(int spetificationId) throws ServiceException{
        BillDAOImpl billDAO = BillDAOImpl.getInstance();
        JobDAOImpl jobDAO = JobDAOImpl.getInstance();
        ProjectDAOImpl projectDAO = ProjectDAOImpl.getInstance();
        SpecificationDAOImpl specificationDAO = SpecificationDAOImpl.getInstance();
        try {
            String name = BillNameBuilder.createBillName(billDAO.getLastBillName());
            int cost = jobDAO.getTotalCostOfSpecJobs(spetificationId);
            Project project = projectDAO.getProject(spetificationId);
            int customer = specificationDAO.getUserId(spetificationId);
            billDAO.createBill(name, customer, project.getId(), project.getManager(), cost);
        } catch (DAOException e) {
            throw new ServiceException(ResourceManager.getProperty(ERROR_SAVE_BILL) + spetificationId, e);
        }
    }

}
