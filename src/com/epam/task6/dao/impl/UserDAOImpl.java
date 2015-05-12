package com.epam.task6.dao.impl;

import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.api.UserDAO;
import com.epam.task6.dao.pool.ConnectionPool;
import com.epam.task6.domain.user.Role;
import com.epam.task6.domain.user.RoleDefiner;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    /** Initializing database activity logger */
    private static Logger logger = Logger.getLogger(UserDAOImpl.class);

    private static int last_number = 0;
    private static int count_on_page = 100;
    /** Logger messages */
    private static final String ERROR_GET_PASSWORD = "logger.db.error.get.password";
    private static final String INFO_GET_PASSWORD = "logger.db.info.get.password";
    private static final String ERROR_GET_USER = "logger.db.error.get.user";
    private static final String INFO_GET_USER = "logger.db.info.get.user";
    private static final String ERROR_GET_USER_ROLE = "logger.db.error.get.user.role";
    private static final String INFO_GET_USER_ROLE = "logger.db.info.get.user.role";
    private static final String DATA_ROLE_NOT_FOUND = "logger.db.data.role.not.found";
    private static final String ERROR_TAKE_EMPLOYEE = "logger.db.error.take.employee";
    private static final String INFO_TAKE_EMPLOYEE = "logger.db.info.take.employee";
    private static final String ERROR_GET_USER_MAIL = "logger.db.error.get.user.mail";
    private static final String ERROR_GET_USER_NAME = "logger.db.error.get.user.name";
    private static final String INFO_GET_USER_MAIL = "logger.db.info.get.user.mail";
    private static final String ERROR_CLOSE = "111";


    /**
     * This query appoints employee to certain job. <br />
     * Requires to set job id and user mail.
     */
    private static final String SQL_TAKE_EMPLOYEE =
            "UPDATE employment SET jid = ? WHERE uid = (SELECT id FROM users WHERE mail = ?)";

    private static final String SQL_FIND_USERS_BY_EMIL_AND_PASSWORD =
            "SELECT * FROM users WHERE email = ? AND password = ?";

    private static final String SQL_SELECT_USERS =
            "SELECT * FROM users WHERE role_id = ?";

    private static final String SQL_FIND_USER_MAIL_BY_ID =
            "SELECT email FROM users WHERE id = ?";

    private static final String SQL_FIND_USER_ROLE_BY_USER_ID =
            "SELECT names FROM roles WHERE id = ?";

    private static final String SQL_FIND_ID_BY_NAME =
            "SELECT id FROM users WHERE firstName = ?";

    private static final String SQL_FIND_ALL_DEV_NAME = "SELECT firstName FROM users WHERE role_id = 3";

    private static final UserDAOImpl instance = new UserDAOImpl();

    public static UserDAOImpl getInstance() { return  instance; }

    /**
     *
     * @param email
     * @param password
     * @return
     * @throws DAOException
     */

    public User checkUserMailAndPassword(String email, String password) throws DAOException {
        User user = null;
        Connection connector = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            preparedStatement = connector.prepareStatement(SQL_FIND_USERS_BY_EMIL_AND_PASSWORD);
            preparedStatement.setBytes(1, email.getBytes());
            preparedStatement.setBytes(2, password.getBytes());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getInt("id"), resultSet.getString("firstName"), resultSet.getString("lastname"),
                        resultSet.getString("qualification"), resultSet.getString("email"),
                        resultSet.getString("password"), getUserRole(resultSet.getInt("role_id")));
            }
        } catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_GET_USER_MAIL) + email, e);
        }
        finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        logger.info(ResourceManager.getProperty(INFO_GET_PASSWORD));
        return user;
    }

    /**
     *
     * @param name
     * @return
     * @throws DAOException
     */
    public int getUserByName(String name) throws DAOException {
        int id = 0;
        Connection connector = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connector.prepareStatement(SQL_FIND_ID_BY_NAME);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                id = resultSet.getInt("id");
            }
        }
        catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_GET_USER_NAME) + id, e);
        }
        finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        logger.info(ResourceManager.getProperty(INFO_GET_USER_MAIL) + id);
        return id;
    }

    /**
     *
     * @return
     * @throws DAOException
     */
    public List<String> getAllDeveloperNames () throws DAOException {
        List<String> userList = new ArrayList<String>();
        Connection connector = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connector.createStatement();
            resultSet = statement.executeQuery(SQL_FIND_ALL_DEV_NAME);
            while (resultSet.next()) {
                userList.add(resultSet.getString(1));
            }
        }
        catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_GET_USER), e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        logger.info(ResourceManager.getProperty(INFO_GET_USER));
        return userList;
    }

    public int getUserById(int role) throws DAOException {
        int id = 0;
        Connection connector = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connector.prepareStatement(SQL_FIND_ID_BY_NAME);
            preparedStatement.setInt(1, role);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                id = resultSet.getInt("id");
            }
        }
        catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_GET_USER_NAME) + id, e);
        }
        finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        logger.info(ResourceManager.getProperty(INFO_GET_USER_MAIL) + id);
        return id;
    }

   /* public User getUsersById1 (int id) throws DAOException {
        User user = null;

        try {
            connector = ConnectionPool.getInstance().getConnection();
            preparedStatement = connector.prepareStatement(SQL_FIND_USER_MAIL_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            int count = 0;
            while (count < last_number && resultSet.next()) {
                count++;
            }
            for (; count < last_number + count_on_page && resultSet.next(); count++) {

                user = new User(resultSet.getInt("id"), resultSet.getString("firstName"), resultSet.getString("lastname"),
                        resultSet.getString("qualification"), resultSet.getString("email"),
                        resultSet.getString("password"), getUserRole(resultSet.getInt("role_id")));

            }
        }
        catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_GET_USER), e);
        } finally {
            try {
                connector.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        logger.info(ResourceManager.getProperty(INFO_GET_USER));
        return user;
    }*/
    /**
     *
     * @param id
     * @return
     * @throws DAOException
     */

    public Role getUserRole(int id) throws DAOException {
        Role role = null;
        Connection connector = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connector.prepareStatement(SQL_FIND_USER_ROLE_BY_USER_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                role = RoleDefiner.defineRole(resultSet.getString("names"));
            }
            if (role == null){
               throw new DAOException(ResourceManager.getProperty(DATA_ROLE_NOT_FOUND) + id);
            }
        } catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_GET_USER_ROLE) + id, e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        logger.info(ResourceManager.getProperty(INFO_GET_USER_ROLE) + id);
        return role;
    }



    /**
     * This method sets job where employee will be busy
     *
     * @param jid Job id
     * @param mail User mail
     * @throws DAOException object if execution of query is failed
     */
    public void takeEmployee(int jid, String mail) throws DAOException {
        Connection connector = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connector.prepareStatement(SQL_TAKE_EMPLOYEE);
            preparedStatement.setInt(1, jid);
            preparedStatement.setBytes(2, mail.getBytes());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_TAKE_EMPLOYEE) + mail, e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connector);
            try {
               preparedStatement.close();
            } catch (SQLException e) {
                logger.error(ResourceManager.getProperty(ERROR_CLOSE));
            }
        }
        logger.info(ResourceManager.getProperty(INFO_TAKE_EMPLOYEE) + mail + "," + jid);
    }

}