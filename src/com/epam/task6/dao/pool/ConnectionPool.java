package com.epam.task6.dao.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    /* Initializes database logger */
//    private static Logger logger = Logger.getLogger("pool");

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
        initPool();
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
    private void initPool()  {
        config = new PoolConfiguration();
        pool = new ArrayBlockingQueue<Connection>(config.getMaxSize(), true);
        try {
            Class.forName(config.getDriver());
            for (int i = 0; i < config.getMinSize(); i++) {
                pool.add(DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPassword()));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
           /// logger.error(ResourceManager.getProperty(LOGGER_LOAD_JDBC_ERROR), e);
        } catch (SQLException e) {
            e.printStackTrace();
           // logger.error(ResourceManager.getProperty(LOGGER_GET_CONNECTION_ERROR), e);
        }
    }

    /**
     * This method returns alive connection with database
     *
     * @return Alive Connection object
     */
    public Connection getConnection() {
        Connection connection = null;
        try {
            if (!pool.isEmpty()) {
                connection = pool.take();
            } else if (pool.size() < config.getMaxSize()) {
                connection = DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPassword());
            } else {
                connection = pool.take();
            }
            if (connection.isClosed()) {
                connection = DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPassword());
            }

           // logger.error(ResourceManager.getProperty(LOGGER_TAKE_CONNECTION_ERROR), exception);
        } catch (SQLException exception) {
            exception.printStackTrace();
           // logger.error(ResourceManager.getProperty(LOGGER_GET_CONNECTION_ERROR), exception);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
                e.printStackTrace();
                //logger.error(ResourceManager.getProperty(LOGGER_PUT_CONNECTION_ERROR), e);
            }
        }
    }

}
