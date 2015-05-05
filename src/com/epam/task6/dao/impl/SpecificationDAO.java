package com.epam.task6.dao.impl;

import com.epam.task6.dao.AbstractDAO;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.connector.DBConnector;
import com.epam.task6.domain.project.Spetification;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olga on 21.04.15.
 */
public class SpecificationDAO extends AbstractDAO {
    private static Logger logger = Logger.getLogger(SpecificationDAO.class);

    private static int last_number = 0;
    private static int count_on_page = 100;
    /**
     * Specification logger messages
     */

    private static final String INFO_GET_LAST_SPEC_ID = "logger.db.info.get.last.spec.id";
    private static final String INFO_SET_SPEC_STATUS = "logger.db.info.set.spec.status";
    private static final String ERROR_GET_USER = "logger.db.error.get.user";
    private static final String ERROR_SAVE_SPEC = "logger.db.error.save.specification";
    private static final String INFO_SAVE_SPEC = "logger.db.info.save.specification";
    private static final String ERROR_SET_SPEC_STATUS = "logger.db.error.set.spec.status";
    private static final String ERROR_GET_LAST_SPEC_ID = "logger.db.error.get.last.spec.id";

    private static final String DEFAULT_SPECIFICATION_STATUS = "waiting";

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

    public static final String SQL_INSERT_NEW_SPECIFICATION_BY_NAME =
            "INSERT INTO specifications (name, jobs, uid, status) VALUES (?, ?, ?, ?)";
    public static final String SQL_INSERT_NEW_SPECIFICATION_BY_UID =
            "INSERT INTO specifications (uid, status) VALUES (?, ?)";


    private static final String SQL_FIND_MANAGER_SPECIFICATION =
            "SELECT name FROM specifications WHERE status = 0";

    public List<String> getManagerSpetification() throws DAOException {

        ArrayList<String> list = new ArrayList<String>();
        connector = new DBConnector();
        try {
            preparedStatement = connector.getPreparedStatement(SQL_FIND_MANAGER_SPECIFICATION);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("name"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            //throw new DAOException(ResourceManager.getProperty(ERROR_GET_MANAGER_PROJECTS) + uid, exception);
        } finally {
            connector.close();
        }
       // logger.info(ResourceManager.getProperty(INFO_GET_MANAGER_PROJECTS) + uid);


        return list;
    }
        public void addSpetificationByNameAndJob(String name, int jobs, int uid, int status){
        connector = new DBConnector();
        try {
            preparedStatement = connector.getPreparedStatement(SQL_INSERT_NEW_SPECIFICATION_BY_NAME);
            preparedStatement.setBytes(1, name.getBytes());
            preparedStatement.setInt(2, jobs);
            preparedStatement.setInt(3, uid);
            preparedStatement.setInt(4, status);
            preparedStatement.execute();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            connector.close();

        }
        System.out.println("OK for addSpeck");
    }

    public void addSpetificationByUidAndStatus(String name, int uid, int status){
        connector = new DBConnector();
        try {
            preparedStatement = connector.getPreparedStatement(SQL_INSERT_NEW_SPECIFICATION_BY_UID);
            preparedStatement.setInt(1,uid);
            preparedStatement.setInt(2, status);
            preparedStatement.execute();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            connector.close();

        }
        System.out.println("OK for addSpeck");
    }


    ////

    public Spetification getSpetificationByName(String name){
        Spetification spetification = null;
        connector = new DBConnector();
        try {
            preparedStatement = connector.getPreparedStatement(SQL_SET_SPECIFICATION_BYNAME);
            preparedStatement.setBytes(1, name.getBytes());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                spetification = new Spetification(name, resultSet.getInt("uid"), resultSet.getInt("jobs"),resultSet.getInt("status"));
                spetification.setStatus(1);
                spetification.setId(resultSet.getInt("id"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            connector.close();

        }
        System.out.println("OK");
        return spetification;

    }

    public Spetification getSpetificationById(int id){
        Spetification spetification = null;
        connector = new DBConnector();
        try {
            preparedStatement = connector.getPreparedStatement(SQL_SET_SPECIFICATION_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                spetification = new Spetification(resultSet.getString("name"), resultSet.getInt("uid"), resultSet.getInt("jobs"),resultSet.getInt("status"));
                spetification.setStatus(1);
                spetification.setId(resultSet.getInt("id"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            connector.close();

        }
        System.out.println("OK for getSpetificationById");
        return spetification;

    }



    public Spetification setSpetificationByNameAndJob(String name, int jobs){
        Spetification spetification = null;
        connector = new DBConnector();
        try {
            preparedStatement = connector.getPreparedStatement(SQL_SET_SPECIFICATION_BY_NAME);
            preparedStatement.setBytes(1, name.getBytes());
            preparedStatement.setInt(2, jobs);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                spetification = new Spetification(resultSet.getInt("id"), jobs, name);
                spetification.setStatus(0);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            connector.close();

        }
        System.out.println("OK");
        return spetification;

    }
    public void setSpecificationUserId (int id, int userId) throws DAOException{
        connector = new DBConnector();
        try {
            preparedStatement = connector.getPreparedStatement(SQL_SET_SPECIFICATION_USER_ID);
            preparedStatement.setInt(1,userId);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.print("11111");
           // throw new DAOException(ResourceManager.getProperty(ERROR_SET_SPEC_STATUS) + userId, e);
        } finally {
            connector.close();
        }
        logger.info(ResourceManager.getProperty(INFO_SET_SPEC_STATUS));

    }



    public void setSpecificationStatus(int id, String status) throws DAOException {
        connector = new DBConnector();
        try {
            preparedStatement = connector.getPreparedStatement(SQL_SET_SPECIFICATION_STATUS_TO);
            preparedStatement.setBytes(1, status.getBytes());
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.print("11111");
            throw new DAOException(ResourceManager.getProperty(ERROR_SET_SPEC_STATUS) + status, e);
        } finally {
            connector.close();
        }
        logger.info(ResourceManager.getProperty(INFO_SET_SPEC_STATUS));
    }

    public int getLastSpecificationId(int userId) throws DAOException {
        connector = new DBConnector();
        int id = 0;
        try {
            preparedStatement = connector.getPreparedStatement(SQL_FIND_LAST_CUSTOMER_SPECIFICATION_ID);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
             throw new DAOException(ResourceManager.getProperty(ERROR_GET_LAST_SPEC_ID) +userId, e);
        } finally {
            connector.close();
        }
        logger.info(ResourceManager.getProperty(INFO_GET_LAST_SPEC_ID) + userId);
        return id;
    }

    public int saveSpecification(int id, String name) throws DAOException {
        connector = new DBConnector();
        try {
            preparedStatement = connector.getPreparedStatement(SQL_INSERT_NEW_SPECIFICATION);
            preparedStatement.setInt(1, id);
            preparedStatement.setBytes(2, (new String(name.getBytes("UTF-8"), "CP1251")).getBytes());
            preparedStatement.setBytes(3, DEFAULT_SPECIFICATION_STATUS.getBytes());
            preparedStatement.execute();
        } catch (SQLException | UnsupportedEncodingException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_SAVE_SPEC) + id, e);
        } finally {
            connector.close();
        }
        logger.info(ResourceManager.getProperty(INFO_SAVE_SPEC) + id);
        return getLastSpecificationId(id);
    }


    public List<Spetification> getUserSpecifications(int id) throws DAOException {
        Spetification spetification = null;
        List<Spetification> jobList = new ArrayList<Spetification>();
        connector = new DBConnector();

        try {
            preparedStatement = connector.getPreparedStatement(SQL_FIND_SPETIFICATION_BY_USER_ID);
            preparedStatement.setInt(1, 1);
            resultSet = preparedStatement.executeQuery();
            int count = 0;
            while (count < last_number && resultSet.next()) {
                count++;
            }
            for (; count < last_number + count_on_page && resultSet.next(); count++) {
                spetification = new Spetification(resultSet.getInt("jobs"), resultSet.getInt("uid"), resultSet.getString("name"), resultSet.getInt("status"));
                System.out.print(resultSet.getString("name"));
                spetification.setId(resultSet.getInt("id"));
                jobList.add(spetification);
            }

        } catch (SQLException e) {
              throw new DAOException(ResourceManager.getProperty(ERROR_GET_USER), e);
        } finally {
            connector.close();

        }

        return jobList;
    }


    public void updateSpetificationName(String name, int id){
        connector = new DBConnector();
        try {
            preparedStatement = connector.getPreparedStatement(SQL_UPDATE_SPECTIFICATION_BY_NAME);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
          //  throw new DAOException(ResourceManager.getProperty(ERROR_SET_JOB_COST) + cost, e);
        } finally {
            connector.close();
        }
        System.out.println("OK for update");

      //  logger.info(ResourceManager.getProperty(INFO_SET_JOB_COST) + id);
    }
    public void updateSpetificationJobs (int jobs, int id){
        connector = new DBConnector();
        try {
            preparedStatement = connector.getPreparedStatement(SQL_UPDATE_SPECTIFICATION_BY_JOBS);
            preparedStatement.setInt(1, jobs);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            //  throw new DAOException(ResourceManager.getProperty(ERROR_SET_JOB_COST) + cost, e);
        } finally {
            connector.close();
        }
        System.out.println("OK for update");

        //  logger.info(ResourceManager.getProperty(INFO_SET_JOB_COST) + id);
    }


}
