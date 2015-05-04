package com.epam.task6.dao.impl;

import com.epam.task6.dao.AbstractDAO;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by olga on 25.04.15.
 */
public class RegisterDAO extends AbstractDAO{

    private static final String REG_EX = "([.[^@\\s]]+)@([.[^@\\s]]+)\\.([a-z]+)";
    private static final Pattern PATTERN = Pattern.compile(REG_EX);

    public void registerUser(String email, String password, String firstName, String secondName) {
        Matcher m = PATTERN.matcher(email);
        try {
            System.out.println("dao " + " email " + email + " password " + password);

            if (m.matches()) {
                String insertTableSQL = "INSERT INTO users (id, email, password, firstName, lastname, role_id, qualification)" + " VALUES (9567, " + "'" + email + "'" + ",'" + password + "'" + ",'" + firstName + "'" + ",'" + secondName + "',1,0);";
                preparedStatement.executeUpdate(insertTableSQL);
            }
        } catch (SQLException e) {
            System.out.println(222);
        }
    }
}
