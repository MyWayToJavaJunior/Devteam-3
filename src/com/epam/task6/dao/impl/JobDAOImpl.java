package com.epam.task6.dao.impl;

import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.api.JobDAO;
import com.epam.task6.dao.pool.ConnectionPool;
import com.epam.task6.domain.project.Job;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olga on 26.04.15.
 */
public class JobDAOImpl implements JobDAO {
    /** Initializing database activity logger */
    private static Logger logger = Logger.getLogger("db");

    /** Logger messages */
    private static final String ERROR_NUMBER_OF_JOBS = "logger.db.error.number.of.jobs";
    private static final String INFO_NUMBER_OF_JOBS = "logger.db.info.number.of.jobs";
    private static final String ERROR_SPECIFICATION_JOBS = "logger.db.error.specification.jobs";
    private static final String INFO_SPECIFICATION_JOBS = "logger.db.info.specification.jobs";
    private static final String ERROR_SAVE_JOB = "logger.db.error.save.job";
    private static final String INFO_SAVE_JOB = "logger.db.info.save.job";
    private static final String ERROR_SET_JOB_COST = "logger.db.error.set.job.cost";
    private static final String INFO_SET_JOB_COST = "logger.db.info.set.job.cost";
    private static final String ERROR_JOB_WHERE_BUSY = "logger.db.error.get.job.where.emp.busy";
    private static final String INFO_JOB_WHERE_BUSY = "logger.db.info.get.job.where.emp.busy";
    private static final String ERROR_COST_OF_SPEC_JOBS = "logger.db.error.get.total.spec.cost";
    private static final String ERROR_CLOSE = "111";

    /**
     * Keeps query which return the number of jobs in specification. <br />
     * Requires to set specification id.
     */
    private static final String SQL_GET_COUNT_JOBS_BY_SPECIFICATION_ID =
            "SELECT COUNT(id) FROM jobs WHERE sid = ?";

    /**
     * Keeps query which return all jobs from certain specification. <br />
     * Requires to set specification id.
     */
    private static final String SQL_FIND_JOBS_BY_SPECIFICATION_ID =
            "SELECT id, name, spetialists_count, qualification, time, cost FROM jobs  WHERE sid = ?";

    /**
     * Keeps query save new job in database. <br />
     * Requires to set: specification id, name of job, number of specislist, <br />
     * qualification of specialist required for job.
     */
    private static final String SQL_INSERT_JOB =
            "INSERT INTO jobs (sid, name, qualification, time) VALUES (?, ?, ?, ?)";


    /**
     * Keeps query which sets cost for job. <br />
     * Requires to set cost and job id.
     */
    private static final String SQL_SET_JOB_COST =
            "UPDATE jobs SET cost = ? WHERE id = ?";

    /**
     * Keeps query which return job where employee busy. <br />
     * Requires to set user id.
     */
    private static final String SQL_FIND_JOB_WHERE_BUSY_EMPLOYEE =
            "SELECT * FROM jobs WHERE id = (SELECT jid FROM employment WHERE uid = ?)";

    /**
     * Keeps query which return total cost of all jobs certain specification. <br />
     * Requires to set specification id.
     */
    private static final String SQL_FIND_TOTAL_COST_OF_SPEC_JOBS =
            "SELECT SUM(cost) FROM jobs WHERE sid = ?";


    private static final JobDAOImpl instance = new JobDAOImpl();
    public static JobDAOImpl getInstance() { return  instance; }

    public int getNumberOfJobsInSpecification(int id) throws DAOException {
        int jobs = 0;
        Connection connector = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            PreparedStatement statement = connector.prepareStatement(SQL_GET_COUNT_JOBS_BY_SPECIFICATION_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                jobs = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_NUMBER_OF_JOBS) + id, e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        logger.info(ResourceManager.getProperty(INFO_NUMBER_OF_JOBS) + id);
        return jobs;
    }

    /**
     * Returns list of jobs in certain specification
     *
     * @param sid Specification id
     * @return List of Jobs
     * @throws com.epam.task6.dao.DAOException object if execution of query is failed
     */
    public List<Job> getSpecificationJobs(int sid) throws DAOException {
        List<Job> jobs = new ArrayList<Job>();
        Connection connector = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connector.prepareStatement(SQL_FIND_JOBS_BY_SPECIFICATION_ID);
            preparedStatement.setInt(1, sid);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Job job = new Job();
                job.setId(resultSet.getInt(1));
                job.setName(resultSet.getString(2));
                job.setSpecialist(resultSet.getInt(3));
                job.setQualification(resultSet.getString(4));
                job.setTime(resultSet.getString(5));
                job.setCost(resultSet.getInt(6));
                jobs.add(job);
            }
        } catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_SPECIFICATION_JOBS) + sid, e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
               preparedStatement.close();
               resultSet.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        logger.info(ResourceManager.getProperty(INFO_SPECIFICATION_JOBS) + sid);
        return jobs;
    }



    /**
     * This method for save new job in database
     *
     * @param sid Specification id
     * @param name Name of job
     * @param qualification Qualification of required specialists
     * @throws com.epam.task6.dao.DAOException object if execution of query is failed
     */
    public void saveJob(int sid, String name, String qualification, String time) throws DAOException {
        Connection connector = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connector.prepareStatement(SQL_INSERT_JOB);
            preparedStatement.setInt(1, sid);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, qualification);
            preparedStatement.setString(4, time);
            preparedStatement.execute();
        } catch (SQLException  e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_SAVE_JOB) + name, e);
        }

        finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        logger.info(ResourceManager.getProperty(INFO_SAVE_JOB) + name);
    }


    /**
     * This method saves cost which defines the manager of project
     * @param id Job id
     * @param cost Cost of job
     * @throws com.epam.task6.dao.DAOException object if execution of query is failed
     */
    public void setJobCost(int id, int cost) throws DAOException {
        Connection connector = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connector.prepareStatement(SQL_SET_JOB_COST);
            preparedStatement.setInt(1, cost);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_SET_JOB_COST) + cost, e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        logger.info(ResourceManager.getProperty(INFO_SET_JOB_COST) + id);
    }

    /**
     * This method return sum of jobs cost of certain specification.
     *
     * @param sid Specification id
     * @return Total cost
     * @throws com.epam.task6.dao.DAOException object if execution of query is failed
     */
    public int getTotalCostOfSpecJobs(int sid) throws DAOException {
        int cost = 0;
        Connection connector = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connector.prepareStatement(SQL_FIND_TOTAL_COST_OF_SPEC_JOBS);
            preparedStatement.setInt(1, sid);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                cost = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_COST_OF_SPEC_JOBS), e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        return cost;
    }


    /**
     * Return Job object in which employee busy
     *
     * @param id Job id
     * @return Job object
     * @throws com.epam.task6.dao.DAOException object if execution of query is failed
     */
    public Job getJobWhereEmployeeBusy (int id) throws DAOException {
        Job job = new Job();
        Connection connector = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connector = ConnectionPool.getInstance().getConnection();
            preparedStatement = connector.prepareStatement(SQL_FIND_JOB_WHERE_BUSY_EMPLOYEE);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                job.setId(resultSet.getInt(1));
                job.setSpecification(resultSet.getInt(2));
                job.setName(resultSet.getString(3));
                job.setTime(resultSet.getString(6));
            }
        } catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_JOB_WHERE_BUSY) + id, e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        logger.info(ResourceManager.getProperty(INFO_JOB_WHERE_BUSY) + id);
        return job;
    }

}
