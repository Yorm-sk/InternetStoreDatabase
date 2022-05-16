package com.solvd.internet_store.dao.jdbc;

import com.solvd.internet_store.exceptions.ConnectionIsNullException;
import com.solvd.internet_store.exceptions.PreparedStatementIsNullException;
import com.solvd.internet_store.utils.SQLConnector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDao {
    private static final Logger LOGGER = LogManager.getLogger(AbstractDao.class);
    private Connection connection;
    protected ResultSet resultSet;
    protected PreparedStatement preparedStatement;

    public void setConnection() {
        connection =  SQLConnector.getInstance().createConnection();
    }

    public void setPreparedStatement(String sqlQuery) {
        try {
            if (connection == null) throw new ConnectionIsNullException();
            preparedStatement =  connection.prepareStatement(sqlQuery);
        } catch (SQLException | ConnectionIsNullException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    public void setPreparedStatement(String sqlQuery, int typeScrollSensitivity, int typeReadSensitivity) {
        try {
            if (connection == null) throw new ConnectionIsNullException();
            preparedStatement =  connection.prepareStatement(sqlQuery, typeScrollSensitivity, typeReadSensitivity);
        } catch (SQLException | ConnectionIsNullException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    public void setResultSet(){
        try {
            if (preparedStatement == null) throw new PreparedStatementIsNullException();
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException | PreparedStatementIsNullException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    public void closeAll(){
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.warn(e.getMessage());
            }
        }
        if (preparedStatement != null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                LOGGER.warn(e.getMessage());
            }
        }

        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.warn(e.getMessage());
            }
        }
    }
}