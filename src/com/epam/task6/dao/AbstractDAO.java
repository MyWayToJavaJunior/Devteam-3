package com.epam.task6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by olga on 19.04.15.
 */
public abstract class AbstractDAO {
    protected ResultSet resultSet;
    protected Connection connector;
    protected PreparedStatement preparedStatement;
    protected Statement statement;
}
