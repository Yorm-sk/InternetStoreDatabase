package com.solvd.internet_store.executors.costumer;

import com.solvd.internet_store.dao.ICostumerDao;
import com.solvd.internet_store.models.Costumer;
import com.solvd.internet_store.models.User;
import com.solvd.internet_store.utils.MyBatisSQLFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MybatisExecutor {
    private static final Logger LOGGER = LogManager.getLogger(MybatisExecutor.class);

    public static void main(String[] args) {
        MyBatisSQLFactory factory = MyBatisSQLFactory.newInstance("myBatis/my_butis_confguration.xml");
        try (SqlSession session = factory.getFactory().openSession()){
            ICostumerDao mapper = session.getMapper(ICostumerDao.class);
            Costumer costumer = mapper.getEntity(3);
            LOGGER.info(costumer);

//            Costumer costumerToAdd = new Costumer("+3806329292", new User(2));
//            mapper.createEntity(costumerToAdd);
//            session.commit();

//            Costumer costumerToUpdate = mapper.getEntity(3);
//            costumerToUpdate.setPhoneNumber("+38 097 34 23 222");
//            costumerToUpdate.setUser(new User(1));
//            mapper.updateEntity(costumerToUpdate);
//            session.commit();

//            Costumer costumerToDelete = mapper.getEntity(3);
//            mapper.deleteEntity(costumerToDelete);
//            session.commit();

            List<Costumer> costumers = mapper.getCostumers();
            LOGGER.info(costumers);
        }
    }
}
