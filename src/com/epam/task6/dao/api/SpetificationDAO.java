package com.epam.task6.dao.api;

import com.epam.task6.dao.DAOException;
import com.epam.task6.domain.project.Spetification;

import java.util.List;

/**
 * Created by olga on 09.05.15.
 */
public interface SpetificationDAO {
    public List<String> getManagerSpetification() throws DAOException;
    public void addSpetificationByNameAndJob(String name, int jobs, int uid, int status) throws DAOException;
    public Spetification getSpetificationByName(String name) throws DAOException;
    public Spetification getSpetificationById(int id) throws DAOException;
    public Spetification setSpetificationByNameAndJob(String name, int jobs) throws DAOException;
    public void setSpecificationUserId (int id, int userId) throws DAOException;
    public void setSpecificationStatus(int id, String status) throws DAOException;
    public int getLastSpecificationId(int userId) throws DAOException;
    public int saveSpecification(int id, String name) throws DAOException;
    public List<Spetification> getUserSpecifications(int id) throws DAOException;
    public List<Spetification> getUserSpecificationsByStatus(int status) throws DAOException;
    public void updateSpetificationName(String name, int id) throws  DAOException;
    public void updateSpetificationJobs (int jobs, int id) throws DAOException;
    public int getUserId(int sid) throws DAOException;
}
