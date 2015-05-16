package com.epam.task6.dao.impl;

import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.api.SpetificationDAO;
import com.epam.task6.dao.pool.ConnectionPool;
import com.epam.task6.domain.project.Spetification;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olga on 21.04.15.
 */
public class SpecificationDAOImpl implements SpetificationDAO {
    private static Logger logger = Logger.getLogger(SpecificationDAOImpl.class);

    private static int last_number = 0;
    private static int count_on_page = 100;
    /**
     * Specification logger messages
     */

    /** Specification logger messages */
    private static final String ERROR_GET_USER_SPECS = "logger.db.error.get.user.specifications";
    private static final String ERROR_GET_USER_SPECS_BY_ID = "logger.db.error.get.user.specifications";
    private static final String ERROR_GET_USER_SPECS_BY_NAME = "logger.db.error.get.user.specifications";
    private static final String ERROR_ADD_USER_SPECS_BY_NAME_AND_JOB = "logger.db.error.get.user.specifications";

    private static final String ERROR_UPDATE_SPECS = "logger.db.error.get.user.specifications";
    private static final String INFO_UPDATE_USER_SPECS = "logger.db.info.get.user.specifications";

    private static final String INFO_GET_USER_SPECS = "logger.db.info.get.user.specifications";

    private static final String ERROR_SAVE_SPEC = "logger.db.error.save.specification";
    private static final String INFO_SAVE_SPEC = "logger.db.info.save.specification";
    private static final String ERROR_GET_LAST_SPEC_ID = "logger.db.error.get.last.spec.id";
    private static final String INFO_GET_LAST_SPEC_ID = "logger.db.info.get.last.spec.id";
    private static final String ERROR_SET_SPEC_STATUS = "logger.db.error.set.spec.status";
    private static final String INFO_SET_SPEC_STATUS = "logger.db.info.set.spec.status";
    private static final String ERROR_GET_AUTHOR_OF_SPEC = "logger.db.error.get.author.id.of.spec";
    private static final String INFO_GET_AUTHOR_OF_SPEC = "logger.db.info.get.author.id.of.spec";

    private static final String DEFAULT_SPECIFICATION_STATUS = "waiting";

    private static final String ERROR_CLOSE = "111";

    private static final String ATTRIBUTE_ID = "id";
    private static final String ATTRIBUTE_NAME = "name";
    private static final String ATTRIBUTE_USERID = "uid";
    private static final String ATTRIBUTE_JOBS = "jobs";
    private static final String ATTRIBUTE_STATUS = "status";


    public static final String SQL_SET_SPECIFICATION_STATUS_TO =
            "UPDATE specifications SET status = ? WHERE id = ?";
    public static final String SQL_SET_SPECIFICATION_USER_ID =
            "UPDATE specifications SET uid = ? WHERE id = ?";

    public static final String SQL_SET_SPECIFICATION_BY_NAME =
            "SELECT * FROM specifications WHERE name = ? AND jobs = ?";

    public static final String SQL_SET_SPECIFICATION_BYNAME =
            "SELECT * FROM specifications WHERE name = ?";

    public static final String SQL_SET_SPECIFICATION_BY_ID =
            "SELECT * FROM specifications WHERE id = ?";


    public static final String SQL_UPDATE_SPECTIFICATION_BY_JOBS =
            "UPDATE specifications SET jobs = ? WHERE id = ?";

    public static final String SQL_UPDATE_SPECTIFICATION_BY_NAME =
            "UPDATE specifications SET name = ? WHERE id = ?";

    public static final String SQL_INSERT_NEW_SPECIFICATION =
            "INSERT INTO specifications (uid, name, status) VALUES (?, ?, ?)";

    public static final String SQL_FIND_LAST_CUSTOMER_SPECIFICATION_ID =
            "SELECT MAX(id) FROM specifications WHERE uid = ?";

    public static final String SQL_FIND_SPECIFICATIONS_BY_CUSTOMER_ID =
            "UPDATE specifications SET name = ? WHERE id = ?";

    private static final String SQL_FIND_SPETIFICATION_BY_USER_ID =
            "SELECT * FROM specifications WHERE uid = ?";

    private static final String SQL_FIND_SPETIFICATION_BY_STATUS =
            "SELECT * FROM specifications WHERE status = ?";

    public static final String SQL_INSERT_NEW_SPECIFICATION_BY_NAME =
            "INSERT INTO specifications (name, jobs, uid, status) VALUES (?, ?, ?, ?)";

    public static final String SQL_INSERT_NEW_SPECIFICATION_BY_UID =
            "INSERT INTO specifications (uid, status) VALUES (?, ?)";


    private static final String SQL_FIND_MANAGER_SPECIFICATION =
            "SELECT name FROM specifications WHERE status = 0";

    /**
     * This query searches user id who created certain specification.
     */
    public static final String SQL_FIND_USER_ID_BY_SPECIFICATION_ID =
            "SELECT uid FROM specifications WHERE id = ?";

    private static final SpecificationDAOImpl instance = new SpecificationDAOImpl();
    public static SpecificationDAOImpl getInstance() { return  instance; }


    public List<String> getManagerSpetification() throws DAOException {
        ArrayList<String> list = new ArrayList<String>();
        Connection connector = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connector.prepareStatement(SQL_FIND_MANAGER_SPECIFICATION);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString(ATTRIBUTE_NAME));
            }
        } catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_GET_USER_SPECS) , e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
            try{
                resultSet.close();
            }
            catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        logger.info(ResourceManager.getProperty(INFO_GET_USER_SPECS));
        return list;
    }


        public void addSpetificationByNameAndJob(String name, int jobs, int uid, int status) throws DAOException {
            Connection connector = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = null;
        try {
            connector = ConnectionPool.getInstance().getConnection();
            preparedStatement = connector.prepareStatement(SQL_INSERT_NEW_SPECIFICATION_BY_NAME);
            preparedStatement.setBytes(1, name.getBytes());
            preparedStatement.setInt(2, jobs);
            preparedStatement.setInt(3, uid);
            preparedStatement.setInt(4, status);
            preparedStatement.execute();
        }
        catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_ADD_USER_SPECS_BY_NAME_AND_JOB) , e);
        }  finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
           logger.info(ResourceManager.getProperty(INFO_GET_USER_SPECS));
    }



    public Spetification getSpetificationByName(String name) throws DAOException {
        Spetification spetification = null;
        Connection connector = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connector.prepareStatement(SQL_SET_SPECIFICATION_BYNAME);
            preparedStatement.setBytes(1, name.getBytes());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                spetification = new Spetification(name, resultSet.getInt(ATTRIBUTE_USERID),
                        resultSet.getInt(ATTRIBUTE_JOBS),resultSet.getInt(ATTRIBUTE_STATUS));
                spetification.setStatus(1);
                spetification.setId(resultSet.getInt(ATTRIBUTE_ID));
            }
        }
        catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_GET_USER_SPECS_BY_NAME) , e);
        }  finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
            try{
                resultSet.close();
            }
            catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        logger.info(ResourceManager.getProperty(INFO_GET_USER_SPECS));
        return spetification;

    }



    public Spetification getSpetificationById(int id) throws DAOException {
        Spetification spetification = null;
        Connection connector = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connector.prepareStatement(SQL_SET_SPECIFICATION_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                spetification = new Spetification(resultSet.getString(ATTRIBUTE_NAME), resultSet.getInt(ATTRIBUTE_USERID),
                        resultSet.getInt(ATTRIBUTE_JOBS),resultSet.getInt(ATTRIBUTE_STATUS));
                spetification.setStatus(1);
                spetification.setId(resultSet.getInt(ATTRIBUTE_ID));
            }
        }
        catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_GET_USER_SPECS_BY_ID) , e);
        }  finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
            try{
                resultSet.close();
            }
            catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        logger.info(ResourceManager.getProperty(INFO_GET_USER_SPECS));
        return spetification;

    }


    public Spetification setSpetificationByNameAndJob(String name, int jobs) throws DAOException {
        Spetification spetification = null;
        Connection connector = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connector.prepareStatement(SQL_SET_SPECIFICATION_BY_NAME);
            preparedStatement.setBytes(1, name.getBytes());
            preparedStatement.setInt(2, jobs);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                spetification = new Spetification(resultSet.getInt(ATTRIBUTE_ID), jobs, name);
                spetification.setStatus(0);
            }
        }
        catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_GET_USER_SPECS_BY_ID) , e);
        }  finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
            try {
                resultSet.close();
            }
            catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        logger.info(ResourceManager.getProperty(INFO_GET_USER_SPECS));
        return spetification;

    }
    public void setSpecificationUserId (int id, int userId) throws DAOException {
        Connection connector = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connector.prepareStatement(SQL_SET_SPECIFICATION_USER_ID);
            preparedStatement.setInt(1,userId);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_SET_SPEC_STATUS) + userId, e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        logger.info(ResourceManager.getProperty(INFO_SET_SPEC_STATUS));

    }

    public void setSpecificationStatus(int id, String status) throws DAOException {
        Connection connector = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connector.prepareStatement(SQL_SET_SPECIFICATION_STATUS_TO);
            preparedStatement.setBytes(1, status.getBytes());
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_SET_SPEC_STATUS) + status, e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        logger.info(ResourceManager.getProperty(INFO_SET_SPEC_STATUS));
    }

    public int getLastSpecificationId(int userId) throws DAOException {
        int id = 0;
        Connection connector = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connector.prepareStatement(SQL_FIND_LAST_CUSTOMER_SPECIFICATION_ID);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
             throw new DAOException(ResourceManager.getProperty(ERROR_GET_LAST_SPEC_ID) +userId, e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
               preparedStatement.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }

            try{
                resultSet.close();
            }
            catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        logger.info(ResourceManager.getProperty(INFO_GET_LAST_SPEC_ID) + userId);
        return id;
    }

    public int saveSpecification(int id, String name) throws DAOException {
        Connection connector = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connector.prepareStatement(SQL_INSERT_NEW_SPECIFICATION);
            preparedStatement.setInt(1, id);
            preparedStatement.setBytes(2, (new String(name.getBytes("UTF-8"), "CP1251")).getBytes());
            preparedStatement.setBytes(3, DEFAULT_SPECIFICATION_STATUS.getBytes());
            preparedStatement.execute();
        } catch (SQLException | UnsupportedEncodingException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_SAVE_SPEC) + id, e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        logger.info(ResourceManager.getProperty(INFO_SAVE_SPEC) + id);
        return getLastSpecificationId(id);
    }


    public List<Spetification> getUserSpecifications(int id) throws DAOException {
        Spetification spetification = null;
        List<Spetification> jobList = new ArrayList<Spetification>();
        Connection connector = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connector.prepareStatement(SQL_FIND_SPETIFICATION_BY_USER_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            int count = 0;
            while (count < last_number && resultSet.next()) {
                count++;
            }
            for (; count < last_number + count_on_page && resultSet.next(); count++) {
                spetification = new Spetification(resultSet.getInt(ATTRIBUTE_JOBS), resultSet.getInt(ATTRIBUTE_USERID),
                        resultSet.getString(ATTRIBUTE_NAME), resultSet.getInt(ATTRIBUTE_STATUS));
                System.out.print(resultSet.getString(ATTRIBUTE_NAME));
                spetification.setId(resultSet.getInt(ATTRIBUTE_ID));
                jobList.add(spetification);
            }

        } catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_GET_USER_SPECS) + id, e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
            try{
                resultSet.close();
            }
            catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        logger.info(ResourceManager.getProperty(INFO_GET_USER_SPECS) + id);
        return jobList;
    }


    public List<Spetification> getUserSpecificationsByStatus(int status) throws DAOException {
        Spetification spetification = null;
        List<Spetification> jobList = new ArrayList<Spetification>();
        Connection connector = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connector.prepareStatement(SQL_FIND_SPETIFICATION_BY_STATUS);
            preparedStatement.setInt(1, 1);
            resultSet = preparedStatement.executeQuery();
            int count = 0;
            while (count < last_number && resultSet.next()) {
                count++;
            }
            for (; count < last_number + count_on_page && resultSet.next(); count++) {
                spetification = new Spetification(resultSet.getInt(ATTRIBUTE_JOBS), resultSet.getInt(ATTRIBUTE_USERID),
                        resultSet.getString(ATTRIBUTE_NAME), resultSet.getInt(ATTRIBUTE_STATUS));

                spetification.setId(resultSet.getInt(ATTRIBUTE_ID));
                jobList.add(spetification);
            }

        } catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_GET_USER_SPECS), e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
            try{
                resultSet.close();
            }
            catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }

        }
        logger.info(ResourceManager.getProperty(INFO_GET_USER_SPECS));
        return jobList;
    }


    public void updateSpetificationName(String name, int id) throws  DAOException {
        Connection connector = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connector.prepareStatement(SQL_UPDATE_SPECTIFICATION_BY_NAME);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();

        } catch (SQLException e) {
           throw new DAOException(ResourceManager.getProperty(ERROR_UPDATE_SPECS) + id, e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        logger.info(ResourceManager.getProperty(INFO_UPDATE_USER_SPECS) + id);
    }


    public void updateSpetificationJobs (int jobs, int id) throws DAOException {
        Connection connector = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connector.prepareStatement(SQL_UPDATE_SPECTIFICATION_BY_JOBS);
            preparedStatement.setInt(1, jobs);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_UPDATE_SPECS) + id, e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        logger.info(ResourceManager.getProperty(INFO_UPDATE_USER_SPECS) + id);
    }


    /**
     * This method returns customer id who created certain specification.
     *
     * @param sid Specification id
     * @return Customer id
     * @throws DAOException object if execution of query is failed
     */
    public int getUserId(int sid) throws DAOException {
        int id = 0;
        Connection connector = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connector.prepareStatement(SQL_FIND_USER_ID_BY_SPECIFICATION_ID);
            preparedStatement.setInt(1, sid);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_GET_AUTHOR_OF_SPEC) + sid, e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
            try{
                resultSet.close();
            }
            catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        logger.info(ResourceManager.getProperty(INFO_GET_AUTHOR_OF_SPEC) + sid);
        return id;
    }

}
