package com.epam.task6.dao.impl;

import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.api.TimeDAO;
import com.epam.task6.dao.pool.ConnectionPool;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by olga on 26.04.15.
 */
public class TimeDAOImpl implements TimeDAO{
    /** Initializes logger */
    private static Logger logger = Logger.getLogger("db");

    /** Logger messages */
    private static final String ERROR_SAVE_ELAPSED = "logger.db.error.save.elapsed";
    private static final String INFO_SAVE_ELAPSED = "logger.db.info.save.elapsed";
    private static final String ERROR_UPDATE_ELAPSED = "logger.db.error.update.elapsed";
    private static final String INFO_UPDATE_ELAPSED = "logger.db.info.update.elapsed";
    private static final String ERROR_GET_ELAPSED = "logger.db.error.get.elapsed";
    private static final String INFO_GET_ELAPSED = "logger.db.info.get.elapsed";
    private static final String ERROR_IS_EXIST_ELAPSED = "logger.db.error.is.exist.elapsed";
    private static final String ERROR_GET_TOTAL_ELAPSED = "logger.db.error.get.total.elapsed";
    private static final String INFO_GET_TOTAL_ELAPSED = "logger.db.info.get.total.elapsed";
    private static final String ERROR_GET_ELAPSED_BY_MAIL = "logger.db.error.get.elapsed.by.mail";
    private static final String INFO_GET_ELAPSED_BY_MAIL = "logger.db.info.get.elapsed.by.mail";

    private static final String ERROR_CLOSE = "111";
    /**
     * This query sets time which employee elapsed on job. <br />
     * Requires to set job id, user id and time.
     */
    private static final String SQL_SET_ELAPSED_TIME =
            "INSERT INTO elapsed (jid, uid, time) VALUES (?, ?, ?)";

    /**
     * This query updates existing elapsed time. <br />
     * Requires to set time, user id and job id.
     */
    private static final String SQL_UPDATE_EXISTING_ELAPSED_TIME =
            "UPDATE elapsed SET time = ? WHERE uid = ? AND jid = ?";

    /**
     * This query searches existing elapsed time. <br />
     * Requires to set job id and user id.
     */
    private static final String SQL_FIND_EXISTING_ELAPSED_TIME =
            "SELECT * FROM elapsed WHERE jid = ? AND uid = ?";

    /**
     * This query searches existing time or not.
     */
    private static final String SQL_IS_EXIST_ELAPSED_TIME =
            "SELECT * FROM elapsed WHERE jid = ? AND uid = ?";

    /**
     * This query calculate total elapsed time on project. <br />
     * Requires to set specification id.
     */
    private static final String SQL_FIND_ELAPSED_TIME_ON_PROJECT_BY_SPECIFICATION =
            "SELECT SUM(time) FROM elapsed WHERE jid IN (SELECT id FROM jobs WHERE sid = ?)";

    /**
     * This query searching how much elapsed certain employee. <br />
     * Requires to set mail of employee.
     */
    private static final String SQL_FIND_EMPLOYEE_ELAPSED_TIME_ON_PROJECT_BY_MAIL =
            "SELECT time FROM elapsed WHERE uid = (SELECT id FROM users WHERE mail = ?)";

    /**
     * Saves elapsed time on certain job.
     *
     * @param uid User id
     * @param jid Job id
     * @param time Time
     * @throws DAOException
     */
    private static final TimeDAOImpl instance = new TimeDAOImpl();
    public static TimeDAOImpl getInstance() { return  instance; }


    public void saveElapsedTime(int uid, int jid, int time) throws DAOException {
       // connector = new DBConnector();
        Connection connector = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connector.prepareStatement(SQL_SET_ELAPSED_TIME);
            preparedStatement.setInt(1, jid);
            preparedStatement.setInt(2, uid);
            preparedStatement.setInt(3, time);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_SAVE_ELAPSED) + uid, e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        logger.info(ResourceManager.getProperty(INFO_SAVE_ELAPSED) + uid);
    }

    /**
     * Updates elapsed time if employee sets not the first time.
     *
     * @param uid User id
     * @param jid Job id
     * @param time Time
     * @throws DAOException
     */
    public void updateElapsedTime(int uid, int jid, int time) throws DAOException {
        Connection connector = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connector.prepareStatement(SQL_UPDATE_EXISTING_ELAPSED_TIME);
            preparedStatement.setInt(1, time);
            preparedStatement.setInt(2, uid);
            preparedStatement.setInt(3, jid);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_UPDATE_ELAPSED) + uid, e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        logger.info(ResourceManager.getProperty(INFO_UPDATE_ELAPSED) + uid);
    }

    /**
     * Returns elapsed time on certain job which employee sets at last time.
     *
     * @param uid Employee id
     * @param jid Job id
     * @return Time which sets employee
     * @throws DAOException object if execution of query is failed
     */
    public int getExistingElapsedTime(int uid, int jid) throws DAOException {
        int time = 0;
        Connection connector = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connector.prepareStatement(SQL_FIND_EXISTING_ELAPSED_TIME);
            preparedStatement.setInt(1, jid);
            preparedStatement.setInt(2, uid);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                time = resultSet.getInt("time");
            }
        } catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_GET_ELAPSED) + uid + "," + jid, e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        logger.info(ResourceManager.getProperty(INFO_GET_ELAPSED) + uid + "," + jid);
        return time;
    }

    /**
     * Checks existing of time sets by employee.
     *
     * @param uid Employee id
     * @param jid Job id
     * @return True if elapsed time is already exist
     * @throws DAOException object if execution of query is failed
     */
    public boolean isExistElapsedTime(int uid, int jid) throws DAOException  {
        boolean exist = false;
        Connection connector = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connector.prepareStatement(SQL_IS_EXIST_ELAPSED_TIME);
            preparedStatement.setInt(1, jid);
            preparedStatement.setInt(2, uid);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                exist = true;
            }
        } catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_IS_EXIST_ELAPSED) + uid + "," + jid, e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        return exist;
    }

    /**
     * Returns total time which elapsed on whole project by all employees.
     *
     * @param sid Specification id
     * @return Total elapsed time
     * @throws DAOException object if execution of query is failed
     */
    public int getTotalElapsedTimeOnProject(int sid) throws DAOException {
        int time = 0;
        Connection connector = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connector = ConnectionPool.getInstance().getConnection();
            preparedStatement = connector.prepareStatement(SQL_FIND_ELAPSED_TIME_ON_PROJECT_BY_SPECIFICATION);
            preparedStatement.setInt(1, sid);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getObject(1) != null) {
                    time = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_GET_TOTAL_ELAPSED) + sid, e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        logger.info(ResourceManager.getProperty(INFO_GET_TOTAL_ELAPSED) + sid);
        return time;
    }

}
