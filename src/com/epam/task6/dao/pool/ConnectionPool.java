package com.epam.task6.dao.pool;

import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    /* Initializes database logger */
    private static Logger logger = Logger.getLogger("pool");

    /* Class fields */
    private static ConnectionPool instance;
    private BlockingQueue<Connection> pool;
    private PoolConfiguration config;

    /* Logger messages */
    private static final String LOGGER_LOAD_JDBC_ERROR = "logger.error.jdbc.load";
    private static final String LOGGER_GET_CONNECTION_ERROR = "logger.error.get.connection";
    private static final String LOGGER_TAKE_CONNECTION_ERROR = "logger.error.take.connection";
    private static final String LOGGER_PUT_CONNECTION_ERROR = "logger.error.put.connection";

    /* Constructor */
    public ConnectionPool() {
        try {
            initPool();
        } catch (ClassNotFoundException e) {
            logger.error(ResourceManager.getProperty(LOGGER_LOAD_JDBC_ERROR), e);
        } catch (SQLException e) {
            logger.error(ResourceManager.getProperty(LOGGER_GET_CONNECTION_ERROR), e);
        }
    }

    /**
     * Helps to realize singleton pattern
     *
     * @return ConnectionPool instance
     */
    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    /**
     * This method initializes pool.
     */
    private void initPool() throws ClassNotFoundException, SQLException {
        config = new PoolConfiguration();
        pool = new ArrayBlockingQueue<Connection>(config.getMaxSize(), true);
            Class.forName(config.getDriver());
            for (int i = 0; i < config.getMinSize(); i++) {
                pool.add(DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPassword()));
            }
    }

    /**
     * This method returns alive connection with database
     *
     * @return Alive Connection object
     */
    public Connection getConnection()  {
        Connection connection = null;

            if (!pool.isEmpty()) {
                try {
                    connection = pool.take();
                } catch (InterruptedException e) {
                    logger.error(ResourceManager.getProperty(LOGGER_GET_CONNECTION_ERROR), e);
                }
            }

        return connection;
    }

    /**
     * This method return used connection to pool
     *
     * @param connection Connection object
     */
    public void returnConnection(Connection connection) {
        if (connection != null) {
            try {
                pool.put(connection);
            } catch (InterruptedException e) {
              logger.error(ResourceManager.getProperty(LOGGER_PUT_CONNECTION_ERROR), e);
            }
        }
    }

}
