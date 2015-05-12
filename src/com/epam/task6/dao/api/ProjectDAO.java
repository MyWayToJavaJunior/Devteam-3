package com.epam.task6.dao.api;

import com.epam.task6.dao.DAOException;
import com.epam.task6.domain.project.Project;

import java.util.List;

/**
 * Created by olga on 09.05.15.
 */
public interface ProjectDAO {
    public void deleteProject (int id) throws DAOException;
    public void updateProjectByName(int id, String name) throws DAOException;
    public void updateProjectByTime(int id, String time) throws DAOException;
    public void updateStatusById(int id, int status) throws DAOException;
    public int returnIdByName (String name) throws DAOException;
    public List<Project> getManagerProjects(int uid) throws DAOException;
    public List<String> getManagerProjectsWithStarus(int uid, int status) throws DAOException;
    public void updateDevId(int id, int devId) throws DAOException;
    public List<Project> getProjectsByStatusAndDivId(int status, int devid) throws DAOException;
    public int saveProject(String name, int mid, int sid, int did, int status) throws DAOException;
    public Project getProject(int sid) throws DAOException;
    public Project getProjectById(int pid) throws DAOException;
    public int getLastProjectId(int mid) throws DAOException;
}
