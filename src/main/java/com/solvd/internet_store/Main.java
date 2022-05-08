package com.solvd.internet_store;

import com.solvd.internet_store.dao.jdbc.UserDao;
import com.solvd.internet_store.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        User user = new UserDao().getEntity(2);
        LOGGER.info(user);
        user.setName("Andriy");
        user.setEmail("an@mail.ru");
        user.setAge((short) 28);
        new UserDao().updateEntity(user);
    }
}
