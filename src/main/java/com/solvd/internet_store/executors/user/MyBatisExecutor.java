package com.solvd.internet_store.executors.user;

import com.solvd.internet_store.dao.IUserDao;
import com.solvd.internet_store.models.User;
import com.solvd.internet_store.utils.MyBatisSQLFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MyBatisExecutor {
    private static final Logger LOGGER = LogManager.getLogger(MyBatisExecutor.class);

    public static void main(String[] args) {
        MyBatisSQLFactory factory = MyBatisSQLFactory.newInstance("myBatis/my_butis_confguration.xml");
        try (SqlSession session = factory.getFactory().openSession()){
            IUserDao mapper = session.getMapper(IUserDao.class);

            User user = mapper.getEntity(19);
            LOGGER.info(user);

            List<User> users = mapper.getUsers();
            LOGGER.info(users);

//            User userToAdd = new User("Vasya", "vas@gmail.com", (short) 29);
//            mapper.createEntity(userToAdd);

//            User userToUpdate= mapper.getEntity(19);
//            userToUpdate.setAge((short) 21);
//            userToUpdate.setEmail("mams@i.ia");
//            userToUpdate.setName("Anatoliy");
//            mapper.updateEntity(userToUpdate);
//            session.commit();


//            User userToDelete = mapper.getEntity(20);
//            mapper.deleteEntity(userToDelete);
//            session.commit();
        }
    }
}
