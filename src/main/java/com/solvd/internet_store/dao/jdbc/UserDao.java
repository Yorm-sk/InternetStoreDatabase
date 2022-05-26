package com.solvd.internet_store.dao.jdbc;

import com.solvd.internet_store.dao.IUserDao;
import com.solvd.internet_store.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDao extends AbstractDao implements IUserDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDao.class);
    private static UserDao userDao;
    private final List<User> users = new ArrayList<>();

    private UserDao() {
    }

    public void downloadAndShowUsersFromUsersTable() {
        fillListInUserDao();
        showAllUsersInUserDAO();
    }

    public void showAllUsersInUserDAO() {
        LOGGER.info("Show users that are in list of UserDAO class");
        listIsEmpty();
        users.forEach(LOGGER::info);
        LOGGER.info("Done...");
    }

    public static UserDao getInstance() {
        if (userDao == null) userDao = new UserDao();
        return userDao;
    }

    public void fillListInUserDao() {
        LOGGER.info("Getting users from SQL table and saving them in UserDao list");
        setConnection();
        setPreparedStatement("SELECT * FROM users");
        setResultSet();
        try {
            while (resultSet.next()) {
                User u = new User(resultSet.getString("name"), resultSet.getString("email"),
                        resultSet.getShort("age"));
                u.setId(resultSet.getLong("id"));
                users.add(u);
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
    }

    public void clearListInDao() {
        users.clear();
    }

    @Override
    public User getEntity(long id) {
//        listIsEmpty();
        LOGGER.info("Get user by id - " + id);
        User user = null;
        setConnection();
        setPreparedStatement("SELECT * FROM users WHERE id = ?");
        try {
            preparedStatement.setLong(1, id);
            setResultSet();
            resultSet.next();
            user = new User(resultSet.getString("name"), resultSet.getString("email"),
                    resultSet.getShort("age"));
            user.setId(resultSet.getLong("id"));
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
        return user;
    }

    @Override
    public void createEntity(User user) {
        listIsEmpty();
        LOGGER.info("Creating new user");
        setConnection();
        setPreparedStatement("INSERT INTO users (name, email, age) VALUES (?, ?, ?)");
        try {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setShort(3, user.getAge());
            preparedStatement.executeUpdate();
            if (!users.isEmpty()) {
                setPreparedStatement("SELECT * FROM users", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                setResultSet();
                resultSet.last();
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
    }

    @Override
    public void deleteEntity(User user) {
        listIsEmpty();
        LOGGER.info("DELETING user with id - " + user.getId());
        setConnection();
        try {
            setPreparedStatement("DELETE FROM users WHERE id = ?");
            preparedStatement.setLong(1, user.getId());
            preparedStatement.executeUpdate();
            if (!users.isEmpty()) users.remove(user);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
    }

    @Override
    public void updateEntity(User user) {
        listIsEmpty();
        LOGGER.info("Updating user with id - " + user.getId());
        setConnection();
        try {
            setPreparedStatement("UPDATE users SET name = ?, email = ?, age = ? WHERE id = ?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setShort(3, user.getAge());
            preparedStatement.setLong(4, user.getId());
            preparedStatement.executeUpdate();
            if (!users.isEmpty()) {
                User u = users.stream().filter(user1 -> user1.getId() == user.getId()).collect(Collectors.toList()).get(0);
                u.setName(user.getName());
                u.setAge(user.getAge());
                u.setEmail(user.getEmail());
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done");
    }

    private void listIsEmpty() {
        if (users.size() == 0) LOGGER.warn("\nUserDao list is empty now\n" +
                "To initialize usersDao list use fillListInUserDao method");
    }

    @Override
    public List<User> getUsers() {
        return null;
    }
}
