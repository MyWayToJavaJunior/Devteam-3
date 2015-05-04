package com.epam.task6.dao;

import com.epam.task6.dao.connector.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by olga on 19.04.15.
 */
public abstract class AbstractDAO {
    protected ResultSet resultSet;
    protected DBConnector connector;
    protected PreparedStatement preparedStatement;
    protected Statement statement;
}
