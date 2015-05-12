package com.epam.task6.dao.impl;

import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.api.QualificationDAO;
import com.epam.task6.dao.pool.ConnectionPool;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olga on 26.04.15.
 */
public class QualificationDAOImpl implements QualificationDAO {
    /** Initializing database activity logger */
    private static Logger logger = Logger.getLogger("db");

    /** Logger messages */
    private static final String ERROR_GET_QUALIFICATIONS = "logger.db.error.get.qualifications";
    private static final String INFO_GET_QUALIFICATIONS = "logger.db.info.get.qualifications";
    private static final String ERROR_DEFINE_QUALIFICATION = "logger.db.error.define.qualification";
    private static final String INFO_DEFINE_QUALIFICATION = "logger.db.info.define.qualification";
    private static final String ERROR_CLOSE = "111";

    /**
     * This query searches unique list of qualifications.
     */
    public static final String SQL_FIND_ALL_QUALIFICATIONS =
            "SELECT DISTINCT qualification FROM qualifications ORDER BY qualification ASC";

    /**
     * This query searches qualification id by qualification name.
     */
    public static final String SQL_GET_QUALIFICATION_ID =
            "SELECT id FROM qualifications WHERE qualification = ?";

    /**
     * Returns unique list of all qualifications
     *
     * @return List of qualifications
     * @throws DAOException object if execution of query is failed
     */

    private static final QualificationDAOImpl instance = new QualificationDAOImpl();
    public static QualificationDAOImpl getInstance() { return  instance; }


    public List<String> getAllQualifications () throws DAOException {
        List<String> list = new ArrayList<String>();
        Connection connector = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connector.createStatement();
            resultSet = statement.executeQuery(SQL_FIND_ALL_QUALIFICATIONS);
            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_GET_QUALIFICATIONS), e);
        } finally {
            try {
                connector.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        logger.info(ResourceManager.getProperty(INFO_GET_QUALIFICATIONS));
        return list;
    }

    /**
     * Return qualification id by name
     *
     * @param name Qualification name
     * @return Qualification id
     * @throws DAOException object if execution of query is failed
     */
    public int defineQualification(String name) throws DAOException {
        int result = 0;
        Connection connector = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connector.prepareStatement(SQL_GET_QUALIFICATION_ID);
            preparedStatement.setBytes(1, name.getBytes());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_DEFINE_QUALIFICATION) + name, e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        logger.info(ResourceManager.getProperty(INFO_DEFINE_QUALIFICATION) + name);
        return result;
    }

}

