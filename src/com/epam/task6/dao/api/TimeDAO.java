package com.epam.task6.dao.api;

import com.epam.task6.dao.DAOException;

/**
 * Created by olga on 09.05.15.
 */
public interface TimeDAO {
    public void saveElapsedTime(int uid, int jid, int time) throws DAOException;
    public void updateElapsedTime(int uid, int jid, int time) throws DAOException;
    public int getExistingElapsedTime(int uid, int jid) throws DAOException;
    public boolean isExistElapsedTime(int uid, int jid) throws DAOException;
    public int getTotalElapsedTimeOnProject(int sid) throws DAOException;

}
