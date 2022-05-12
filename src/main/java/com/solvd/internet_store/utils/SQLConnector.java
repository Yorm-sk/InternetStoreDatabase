package com.solvd.internet_store.utils;

import com.solvd.internet_store.exceptions.ConnectionIsNullException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnector {
    private final static Logger LOGGER = LogManager.getLogger(SQLConnector.class);
    private static SQLConnector instance;

    private SQLConnector (){
        try {
            LOGGER.info("Register drive...");
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    public static SQLConnector getInstance(){
        if (instance == null) instance = new SQLConnector();
        return instance;
    }

    public static Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(MyDBProperties.url, MyDBProperties.username, MyDBProperties.password);
        } catch (SQLException throwables) {
            LOGGER.warn("Connection is not happened");
        }

        if (connection == null) try {
            throw new ConnectionIsNullException();
        } catch (ConnectionIsNullException e) {
            LOGGER.warn(e.getMessage());
        }
        return connection;
    }
}
