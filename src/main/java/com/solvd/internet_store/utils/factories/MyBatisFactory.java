package com.solvd.internet_store.utils.factories;

import com.solvd.internet_store.dao.mybatis.AbstractDao;
import com.solvd.internet_store.dao.mybatis.CostumerDao;
import com.solvd.internet_store.dao.mybatis.UserDao;
import com.solvd.internet_store.enums.ModelType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyBatisFactory extends Factory {
    private final static Logger LOGGER = LogManager.getLogger(MyBatisFactory.class);

    public AbstractDao createDao(ModelType type){
        switch (type){
            case USER:
                return new UserDao();
            case COSTUMER:
                return new CostumerDao();
            default:
                LOGGER.warn("No such dao type");
                return null;
        }
    }
}
