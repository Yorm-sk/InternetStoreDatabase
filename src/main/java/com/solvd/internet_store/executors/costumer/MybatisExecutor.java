package com.solvd.internet_store.executors.costumer;

import com.solvd.internet_store.dao.mybatis.CostumerDao;
import com.solvd.internet_store.enums.ModelType;
import com.solvd.internet_store.models.Costumer;
import com.solvd.internet_store.utils.builders.Builder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MybatisExecutor {
    private static final Logger LOGGER = LogManager.getLogger(MybatisExecutor.class);

    public static void main(String[] args) {
        CostumerDao costumerDao = new CostumerDao();
        LOGGER.info(costumerDao.getEntity(1));

        Costumer costumer = (Costumer) Builder.createModel(ModelType.COSTUMER);
        LOGGER.info(costumer);
//        Costumer costumerToAdd = new Costumer("+3806329292", new User(2));
//        costumerDao.createEntity(costumerToAdd);
//
//        Costumer costumerToUpdate = costumerDao.getEntity(3);
//        costumerToUpdate.setPhoneNumber("+38 097 34 23 222");
//        costumerToUpdate.setUser(new User(1));
//        costumerDao.updateEntity(costumerToUpdate);
//
//        Costumer costumerToDelete = costumerDao.getEntity(3);
//        costumerDao.deleteEntity(costumerToDelete);
//        LOGGER.info(costumerDao.getCostumers());

    }
}
