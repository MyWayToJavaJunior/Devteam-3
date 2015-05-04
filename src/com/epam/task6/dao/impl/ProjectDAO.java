package com.epam.task6.dao.impl;

import com.epam.task6.dao.AbstractDAO;
import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.connector.DBConnector;
import com.epam.task6.domain.project.Project;
import com.epam.task6.resource.ResourceManager;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olga on 26.04.15.
 */
public class ProjectDAO extends AbstractDAO {
    /** Initializing database activity logger */
    private static Logger logger = Logger.getLogger("db");

    private static int last_number = 0;
    private static int count_on_page = 100;

    /** Logger messages */
    private static final String ERROR_GET_MANAGER_PROJECTS = "logger.db.error.get.manager.projects";
    private static final String INFO_GET_MANAGER_PROJECTS = "logger.db.info.get.manager.projects";
    private static final String ERROR_SAVE_PROJECT = "logger.db.error.save.project";
    private static final String INFO_SAVE_PROJECT = "logger.db.info.save.project";
    private static final String ERROR_GET_PROJECT = "logger.db.error.get.project";
    private static final String INFO_GET_PROJECT = "logger.db.info.get.project";
    private static final String ERROR_GET_PROJECT_BY_ID = "logger.db.error.get.project.by.id";
    private static final String INFO_GET_PROJECT_BY_ID = "logger.db.info.get.project.by.id";
    private static final String ERROR_GET_LAST_PROJECT_ID = "logger.db.error.get.last.project.id";
    private static final String INFO_GET_LAST_PROJECT_ID = "logger.db.info.get.last.project.id";

    /**
     * Keeps query which return project created by certain manager. <br />
     * Requires to set manager id.
     */
    private static final String SQL_FIND_MANAGER_PROJECTS =
            "SELECT * FROM projects WHERE manager = ?";
    private static final String SQL_FIND_MANAGER_PROJECTS_WITH_STATUS =
            "SELECT name FROM projects WHERE manager = ? AND status = ? AND devid = 0";

    private static final String SQL_FIND_PROJECTS_BY_STATUS_AND_DIVID=
            "SELECT * FROM projects WHERE status = ? AND devid = ?;";
    /**
     * Keeps query which saves new project in database. <br />
     * Requires to set: project name, manager is, specification id.
     */
    private static final String SQL_SAVE_PROJECT =
            "INSERT INTO projects (name, manager, sid) VALUES (?, ?, ?)";

    /**
     * Keeps query which return project created for certain specification. <br />
     * Requires to set specification id.
     */
    private static final String SQL_FIND_PROJECT_BY_SPECIFICATION_ID =
            "SELECT * FROM projects WHERE sid = ?";

    /**
     * Keeps query which searches last project created by certain manager. <br />
     * Requires to set manager id.
     */
    private static final String SQL_FIND_LAST_MANAGER_PROJECT_ID =
            "SELECT id FROM projects WHERE manager = ? ORDER BY id DESC LIMIT 1 ";

    /**
     * Keeps query which searches project by specification id.
     */
    private static final String SQL_FIND_PROJECT_BY_PROJECT_ID =
            "SELECT * FROM projects WHERE id = ?";

    private static final String SQL_FIND_ID_BY_NAME =
            "SELECT id FROM projects WHERE name = ?";

    private static final String SQL_UPDATE_STATUS =
            "UPDATE projects SET status = ? WHERE id = ?";

    private static final String SQL_UPDATE_DEV_ID =
            "UPDATE projects SET devid = ? WHERE id = ?";


    public void updateStatusById(int id, int status) throws DAOException{
        connector = new DBConnector();
        try {
            preparedStatement = connector.getPreparedStatement(SQL_UPDATE_STATUS);
            preparedStatement.setInt(1, status);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_SAVE_PROJECT) + id, e);
        } finally {
            connector.close();
        }
        logger.info(ResourceManager.getProperty(INFO_SAVE_PROJECT) + id);
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
            throw new DAOException(ResourceManager.getProperty(ERROR_SAVE_PROJECT) + id, e);
        } finally {
            connector.close();
        }
        return id;
    }
    /**
     * This method returns list of all manager projects.
     *
     * @param uid Manager id
     * @return List of projects
     * @throws DAOException object if execution of query is failed
     */
    public List<Project> getManagerProjects(int uid) throws DAOException {
        ArrayList<Project> list = new ArrayList<Project>();
        connector = new DBConnector();
        Project project = null;
        try {
            preparedStatement = connector.getPreparedStatement(SQL_FIND_MANAGER_PROJECTS);
            preparedStatement.setInt(1, uid);
            resultSet = preparedStatement.executeQuery();

            int count = 0;
            while (count < last_number && resultSet.next()) {
                count++;
            }
            for (; count < last_number + count_on_page && resultSet.next(); count++) {
               project = new Project(resultSet.getInt("id"),resultSet.getString("name"), resultSet.getInt("sid"), resultSet.getString("time"),resultSet.getInt("devid"), resultSet.getInt("manager"),resultSet.getInt("status"));
                System.out.print(resultSet.getString("name"));
                list.add(project);
            }
        } catch (SQLException exception) {
            throw new DAOException(ResourceManager.getProperty(ERROR_GET_MANAGER_PROJECTS) + uid, exception);
        } finally {
            connector.close();
        }
        logger.info(ResourceManager.getProperty(INFO_GET_MANAGER_PROJECTS) + uid);
        return list;
    }



    /**
     * This method returns list of all manager projects.
     *
     * @param uid Manager id
     * @return List of projects
     * @throws DAOException object if execution of query is failed
     */
    public List<String> getManagerProjectsWithStarus(int uid, int status) throws DAOException {

        ArrayList<String> list = new ArrayList<String>();

            connector = new DBConnector();
            try {
                preparedStatement = connector.getPreparedStatement(SQL_FIND_MANAGER_PROJECTS_WITH_STATUS);
                preparedStatement.setInt(1, uid);
                preparedStatement.setInt(2, status);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {

                    list.add(resultSet.getString("name"));


                }
            } catch (SQLException exception) {
                throw new DAOException(ResourceManager.getProperty(ERROR_GET_MANAGER_PROJECTS) + uid, exception);
            } finally {
                connector.close();
            }
            logger.info(ResourceManager.getProperty(INFO_GET_MANAGER_PROJECTS) + uid);

        return list;
    }

    public void updateDevId(int id, int devId) throws DAOException {
        try {
            preparedStatement = connector.getPreparedStatement(SQL_UPDATE_DEV_ID);
            preparedStatement.setInt(1, devId);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
           throw new DAOException(ResourceManager.getProperty(ERROR_SAVE_PROJECT) + id, e);
        } finally {
            connector.close();
        }
        logger.info(ResourceManager.getProperty(INFO_SAVE_PROJECT) + id);
    }

    public List<Project> getProjectsByStatusAndDivId(int status, int devid) throws DAOException{

        ArrayList<Project> list = new ArrayList<Project>();
        connector = new DBConnector();
        Project project = null;
        try {
            preparedStatement = connector.getPreparedStatement(SQL_FIND_PROJECTS_BY_STATUS_AND_DIVID);
            preparedStatement.setInt(1, status);
            preparedStatement.setInt(2, devid);


            resultSet = preparedStatement.executeQuery();

            int count = 0;
            while (count < last_number && resultSet.next()) {
                count++;
            }
            for (; count < last_number + count_on_page && resultSet.next(); count++) {
                project = new Project(resultSet.getInt("id"),resultSet.getString("name"), resultSet.getInt("sid"), resultSet.getString("time"),resultSet.getInt("devid"), resultSet.getInt("manager"),resultSet.getInt("status"));
                System.out.print(resultSet.getString("name"));
                list.add(project);
            }
        } catch (SQLException exception) {
            throw new DAOException(ResourceManager.getProperty(ERROR_GET_MANAGER_PROJECTS) + devid, exception);
        } finally {
            connector.close();
        }
        logger.info(ResourceManager.getProperty(INFO_GET_MANAGER_PROJECTS) + devid);
        return list;
    }
    /**
     * This method saves new project in database.
     *
     * @param name Project name
     * @param mid Manager id
     * @param sid Specification id
     * @return Id of saved project
     * @throws DAOException object if execution of query is failed
     */
    public int saveProject(String name, int mid, int sid) throws DAOException {
        connector = new DBConnector();
        try {
            preparedStatement = connector.getPreparedStatement(SQL_SAVE_PROJECT);
            preparedStatement.setBytes(1, new String(name.getBytes("ISO-8859-1"), "CP1251").getBytes());
            preparedStatement.setInt(2, mid);
            preparedStatement.setInt(3, sid);
            preparedStatement.execute();
        } catch (SQLException | UnsupportedEncodingException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_SAVE_PROJECT) + name, e);
        } finally {
            connector.close();
        }
        logger.info(ResourceManager.getProperty(INFO_SAVE_PROJECT) + sid);
        return getLastProjectId(mid);
    }

    /**
     * This method returns project by specification id.
     *
     * @param sid Specification id
     * @return Project object
     * @throws DAOException object if execution of query is failed
     */
    public Project getProject(int sid) throws DAOException {
        Project project = new Project();
        connector = new DBConnector();
        try {
            preparedStatement = connector.getPreparedStatement(SQL_FIND_PROJECT_BY_SPECIFICATION_ID);
            preparedStatement.setInt(1, sid);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setManager(resultSet.getInt("manager"));
                project.setSpetification_id(resultSet.getInt("sid"));
            }
        } catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_GET_PROJECT) + sid, e);
        } finally {
            connector.close();
        }
        logger.info(ResourceManager.getProperty(INFO_GET_PROJECT) + sid);
        return project;
    }

    /**
     * This method returns project object by project id.
     *
     * @param pid Project id
     * @return Project object
     * @throws DAOException object if execution of query is failed
     */
    public Project getProjectById(int pid) throws DAOException {
        Project project = new Project();
        connector = new DBConnector();
        try {
            preparedStatement = connector.getPreparedStatement(SQL_FIND_PROJECT_BY_PROJECT_ID);
            preparedStatement.setInt(1, pid);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setManager(resultSet.getInt("manager"));
                project.setSpetification_id(resultSet.getInt("sid"));
            }
        } catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_GET_PROJECT_BY_ID) + pid, e);
        } finally {
            connector.close();
        }
        logger.info(ResourceManager.getProperty(INFO_GET_PROJECT_BY_ID) + pid);
        return project;
    }

    /**
     * This method returns last created project by certain manager.
     *
     * @param mid Manager id
     * @return Last created project id created by manager
     * @throws DAOException object if execution of query is failed
     */
    public int getLastProjectId(int mid) throws DAOException {
        int id = 0;
        connector = new DBConnector();
        try {
            preparedStatement = connector.getPreparedStatement(SQL_FIND_LAST_MANAGER_PROJECT_ID);
            preparedStatement.setInt(1, mid);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException(ResourceManager.getProperty(ERROR_GET_LAST_PROJECT_ID) + mid, e);
        } finally {
            connector.close();
        }
        logger.info(ResourceManager.getProperty(INFO_GET_LAST_PROJECT_ID) + mid);
        return id;
    }

}
