package com.solvd.internet_store.connector;

import com.solvd.internet_store.propeties.MyDBProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnector {
    private final static Logger LOGGER = LogManager.getLogger(SQLConnector.class);

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.warn(e.getMessage());
            }
        }
    }

    public static Connection createConnection() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(MyDBProperties.url, MyDBProperties.username, MyDBProperties.password);
        } catch (SQLException throwables) {
            LOGGER.warn("Connection is not happened");
        }

        if (conn == null) try {
            throw new Exception("Your connection is null");
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
        return conn;
    }
}
