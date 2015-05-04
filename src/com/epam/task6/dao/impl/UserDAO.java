package com.epam.task6.dao.impl;

import com.epam.task6.dao.AbstractDAO;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.connector.DBConnector;
import com.epam.task6.domain.user.Role;
import com.epam.task6.domain.user.RoleDefiner;
import com.epam.task6.domain.user.User;
import com.epam.task6.resource.ResourceManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO {

    /** Initializing database activity logger */
//    private static Logger logger = Logger.getLogger("db");

    /** Logger messages */

    private static final String ERROR_GET_USER_ROLE = "logger.db.error.get.user.role";
    private static final String INFO_GET_USER_ROLE = "logger.db.info.get.user.role";
    private static final String DATA_ROLE_NOT_FOUND = "logger.db.data.role.not.found";
    private static final String ERROR_GET_USER_MAIL = "logger.db.error.get.user.mail";
    private static final String INFO_GET_USER_MAIL = "logger.db.info.get.user.mail";

    private static final String SQL_FIND_USERS_BY_EMIL_AND_PASSWORD =
            "SELECT * FROM users WHERE email = ? AND password = ?";

    private static final String SQL_FIND_USER_MAIL_BY_ID =
            "SELECT email FROM users WHERE id = ?";

    private static final String SQL_FIND_USER_ROLE_BY_USER_ID =
            "SELECT names FROM roles WHERE id = ?";

    private static final String SQL_FIND_ID_BY_NAME =
            "SELECT id FROM users WHERE firstName = ?";

    private static final String SQL_FIND_ALL_DEV_NAME = "SELECT firstName FROM users WHERE role_id = 3";

    //   "SELECT role.name FROM users INNER JOIN role ON users.rid = role.id WHERE users.id = ?";

    private static final String SQL_CHANGE_UI_LANGUAGE =
            "UPDATE users SET `language` = ? WHERE id = ?";

    public UserDAO() {
        super();
    }


    public User checkUserMailAndPassword(String email, String password)  {
        User user = null;
        connector = new DBConnector();
        try {

            preparedStatement = connector.getPreparedStatement(SQL_FIND_USERS_BY_EMIL_AND_PASSWORD);
            preparedStatement.setBytes(1, email.getBytes());
            preparedStatement.setBytes(2, password.getBytes());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getInt("id"), resultSet.getString("firstName"), resultSet.getString("lastname"),
                        resultSet.getString("qualification"), resultSet.getString("email"),
                        resultSet.getString("password"), getUserRole(resultSet.getInt("role_id")));
            }
        } catch (SQLException e) {
          e.printStackTrace();
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            connector.close();

        }
        return user;
    }


    public int returnIdByName (String name) throws DAOException {
        connector = new DBConnector();
        int id = 0;
        try {
            preparedStatement = connector.getPreparedStatement(SQL_FIND_ID_BY_NAME);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                id = resultSet.getInt("id");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            //throw new DAOException(ResourceManager.getProperty(ERROR_SAVE_PROJECT) + id, e);
        } finally {
            connector.close();
        }
        return id;
    }

    public String getUserMail(int id) throws DAOException {
        String mail = "";
        connector = new DBConnector();
        try {
            preparedStatement = connector.getPreparedStatement(SQL_FIND_USER_MAIL_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                mail = resultSet.getString("email");
            }
        } catch (SQLException e) {
           throw new DAOException(ResourceManager.getProperty(ERROR_GET_USER_MAIL) + id, e);
        } finally {
            connector.close();
        }
      //logger.info(ResourceManager.getProperty(INFO_GET_USER_MAIL) + id);
        return mail;
    }

    public List<String> getAllDeveloperNames () throws DAOException{
        connector = new DBConnector();
        List<String> userList = new ArrayList<String>();

        try {
            statement = connector.getStatement();
            resultSet = statement.executeQuery(SQL_FIND_ALL_DEV_NAME);
            while (resultSet.next()) {
                userList.add(resultSet.getString(1));
            }
        }
        catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty("error"), e);
        } finally {
            connector.close();
        }

        return userList;
    }

    public Role getUserRole(int id) throws DAOException {
        connector = new DBConnector();
        Role role = null;
        try {
            preparedStatement = connector.getPreparedStatement(SQL_FIND_USER_ROLE_BY_USER_ID);
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
            connector.close();
        }
       // logger.info(ResourceManager.getProperty(INFO_GET_USER_ROLE) + id);
        return role;
    }

    public void changeUILanguage(String lang, int uid) throws DAOException {
        connector = new DBConnector();
        try {
            preparedStatement = connector.getPreparedStatement(SQL_CHANGE_UI_LANGUAGE);
            preparedStatement.setBytes(1, lang.getBytes());
            preparedStatement.setInt(2, uid);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException("", e);
        } finally {
            connector.close();
        }
    }


}