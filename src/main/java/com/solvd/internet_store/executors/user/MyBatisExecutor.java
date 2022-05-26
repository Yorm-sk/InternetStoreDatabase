package com.solvd.internet_store.executors.user;

import com.solvd.internet_store.dao.IUserDao;
import com.solvd.internet_store.models.User;
import com.solvd.internet_store.utils.MyBatisSQLFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyBatisExecutor {
    private static final Logger LOGGER = LogManager.getLogger(MyBatisExecutor.class);

    public static void main(String[] args) {
        MyBatisSQLFactory factory = MyBatisSQLFactory.newInstance("myBatis/my_butis_confguration.xml");
        SqlSessionFactory sessionFactory = factory.getFactory();
        try (SqlSession session = sessionFactory.openSession()){
            IUserDao mapper = session.getMapper(IUserDao.class);
            User user = mapper.getEntity(1);
            LOGGER.info(user);
        }
    }
}
