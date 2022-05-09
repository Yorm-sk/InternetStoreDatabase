package com.solvd.internet_store;

import com.solvd.internet_store.dao.jdbc.UserDao;
import com.solvd.internet_store.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        UserDao userDao = UserDao.getInstance();
        userDao.showAllUsers();
        User userToUpdate = userDao.getEntity(3);
        userToUpdate.setName("Vadim");
        userToUpdate.setEmail("rvad@gmail.com");
        userToUpdate.setAge((short) 27);
        userDao.updateEntity(userToUpdate);
        userDao.showAllUsers();
    }
}
