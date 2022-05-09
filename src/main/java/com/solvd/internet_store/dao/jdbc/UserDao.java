package com.solvd.internet_store.dao.jdbc;

import com.solvd.internet_store.connector.SQLConnector;
import com.solvd.internet_store.dao.IUserDao;
import com.solvd.internet_store.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDao implements IUserDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDao.class);
    private static UserDao userDao;
    private final List<User> users = new ArrayList<>();

    private UserDao(){
        Connection conn = SQLConnector.createConnection();
        try (PreparedStatement prepSt = conn.prepareStatement("SELECT * FROM users")){
            ResultSet rs = prepSt.executeQuery();
            while (rs.next()){
                User u = new User(rs.getString("name"), rs.getString("email"),
                        rs.getShort("age"));
                u.setId(rs.getLong("id"));
                users.add(u);
            }
        } catch (SQLException e){
            LOGGER.warn(e.getMessage());
        } catch (NullPointerException e){
            LOGGER.warn("User is empty");
        }
        SQLConnector.closeConnection(conn);
    }

    public void showAllUsers(){
        users.forEach(LOGGER::info);
    }

    public static UserDao getInstance(){
        if (userDao == null) userDao = new UserDao();
        return userDao;
    }

    @Override
    public User getEntity(long id) {
        Connection conn = SQLConnector.createConnection();
        User user = null;
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM users WHERE id = ?")){
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            user = new User(rs.getString("name"), rs.getString("email"),
                    rs.getShort("age"));
            user.setId(rs.getLong("id"));
            rs.close();
        } catch (SQLException e){
            LOGGER.warn(e.getMessage());
        }
        SQLConnector.closeConnection(conn);
        return user;
    }

    @Override
    public void createEntity(User user) {
        LOGGER.info("Creating new user");
        Connection conn = SQLConnector.createConnection();
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "INSERT INTO users (name, email, age) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setShort(3, user.getAge());
            preparedStatement.executeUpdate();
            users.add(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        SQLConnector.closeConnection(conn);
        LOGGER.info("Done...");
    }

    @Override
    public void deleteEntity(User user) {
        LOGGER.info("DELETING user with id - " + user.getId());
        Connection conn = SQLConnector.createConnection();
        try (PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM users WHERE id = ?")){
            preparedStatement.setLong(1, user.getId());
            preparedStatement.executeUpdate();
            users.remove(user);
        } catch (SQLException e){
            LOGGER.warn(e.getMessage());
        }
        SQLConnector.closeConnection(conn);
        LOGGER.info("Done...");
    }

    @Override
    public void updateEntity(User user) {
        LOGGER.info("Updating user with id - " + user.getId());
        Connection conn = SQLConnector.createConnection();
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "UPDATE users SET name = ?, email = ?, age = ? WHERE id = ?")){
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setShort(3, user.getAge());
            preparedStatement.setLong(4, user.getId());
            preparedStatement.executeUpdate();
            User u = users.stream().filter(user1 -> user1.getId() == user.getId()).collect(Collectors.toList()).get(0);
            u.setName(user.getName());
            u.setAge(user.getAge());
            u.setEmail(user.getEmail());
        } catch (SQLException e){
            LOGGER.warn(e.getMessage());
        }
        SQLConnector.closeConnection(conn);
        LOGGER.info("Done");
    }
}
