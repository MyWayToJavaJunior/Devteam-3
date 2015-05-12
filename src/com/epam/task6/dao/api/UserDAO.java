package com.epam.task6.dao.api;

import com.epam.task6.dao.DAOException;
import com.epam.task6.domain.user.Role;
import com.epam.task6.domain.user.User;

import java.util.List;

/**
 * Created by olga on 08.05.15.
 */
public interface UserDAO {

     public User checkUserMailAndPassword(String email, String password) throws DAOException;
     public int getUserByName(String name) throws DAOException;
     public List<String> getAllDeveloperNames () throws DAOException;
     public int getUserById(int role) throws DAOException;
     public Role getUserRole(int id) throws DAOException;
     public void takeEmployee(int jid, String mail) throws DAOException;
}
