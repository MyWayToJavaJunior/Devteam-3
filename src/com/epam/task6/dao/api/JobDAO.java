package com.epam.task6.dao.api;

import com.epam.task6.dao.DAOException;
import com.epam.task6.domain.project.Job;

import java.util.List;

/**
 * Created by olga on 09.05.15.
 */
public interface JobDAO {
    public List<Job> getSpecificationJobs(int sid) throws DAOException;
    public void saveJob(int sid, String name, String qualification, String time) throws DAOException;
    public void setJobCost(int id, int cost) throws DAOException;
    public int getTotalCostOfSpecJobs(int sid) throws DAOException;
    public Job getJobWhereEmployeeBusy (int id) throws DAOException;
}
