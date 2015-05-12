package com.epam.task6.dao.api;

import com.epam.task6.dao.DAOException;

import java.util.List;

/**
 * Created by olga on 09.05.15.
 */
public interface QualificationDAO {
    public List<String> getAllQualifications () throws DAOException;
    public int defineQualification(String name) throws DAOException;
}
