package com.solvd.internet_store.executors.user;

import com.solvd.internet_store.dao.IUserDao;
import com.solvd.internet_store.dao.mybatis.UserDao;
import com.solvd.internet_store.models.User;
import com.solvd.internet_store.utils.MyBatisSQLFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MyBatisExecutor {
    private static final Logger LOGGER = LogManager.getLogger(MyBatisExecutor.class);

    public static void main(String[] args) {
        UserDao userDao = new UserDao();

        LOGGER.info(userDao.getEntity(1));

        LOGGER.info(userDao.getUsers());

            User userToAdd = new User("Vasya", "vas@gmail.com", (short) 29);
           userDao.createEntity(userToAdd);

//            User userToUpdate= userDao.getEntity(18);
//            userToUpdate.setAge((short) 21);
//            userToUpdate.setEmail("mams@i.ia");
//            userToUpdate.setName("Anatoliy");
//            userDao.updateEntity(userToUpdate);
//
//
//            User userToDelete = userDao.getEntity(20);
//            userDao.deleteEntity(userToDelete);
    }
}
