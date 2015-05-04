package com.epam.task6.dao.connector;

import com.epam.task6.dao.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by olga on 19.04.15.
 */
public class DBConnector {
    /** Initializes database logger */
//    private static Logger logger = Logger.getLogger("db");

    /** logger messages */
    private static final String MSG_CREATE_STATEMENT_ERROR = "logger.connector.create.statement.error";
    private static final String MSG_CREATE_PREPARED_ERROR = "logger.connector.create.prepared.error";
    private static final String MSG_CLOSE_STATEMENT_ERROR = "logger.connector.close.statement.error";
    private static final String MSG_CLOSE_PREPARED_ERROR = "logger.connector.close.prepared.error";

    /** Connection pool instance */
    private ConnectionPool pool = ConnectionPool.getInstance();

    /** Class fields */
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    /**
     * Constructor
     */
    public DBConnector() {
        connection = pool.getConnection();
    }

    /**
     * Return preparedStatement object
     *
     * @return Statement object
     */
    public Statement getStatement() {
        if (statement == null) {
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
               // logger.error(ResourceManager.getProperty(MSG_CREATE_STATEMENT_ERROR), e);
            }
        }
        return statement;
    }

    /**
     * Returns prepared preparedStatement object
     *
     * @param query Query for prepared preparedStatement
     * @return PreparedStatement object
     */
    public PreparedStatement getPreparedStatement(String query) {
        if (preparedStatement == null) {
            try {
                preparedStatement = connection.prepareStatement(query);
            } catch (SQLException e) {
              //  logger.error(ResourceManager.getProperty(MSG_CREATE_PREPARED_ERROR), e);
            }
        }
        return preparedStatement;
    }

    /**
     * Need to be caused when work with connector finished
     */
    public void close() {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
              //  logger.error(ResourceManager.getProperty(MSG_CLOSE_STATEMENT_ERROR), e);
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
              //  logger.error(ResourceManager.getProperty(MSG_CLOSE_PREPARED_ERROR), e);
            }
        }
        if (connection != null) {
           pool.returnConnection(connection
           );
        }
    }

}
