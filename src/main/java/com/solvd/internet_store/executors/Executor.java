package com.solvd.internet_store.executors;

import com.solvd.internet_store.dao.IBaseDao;
import com.solvd.internet_store.enums.FactoryTypes;
import com.solvd.internet_store.enums.ModelType;
import com.solvd.internet_store.models.User;
import com.solvd.internet_store.utils.builders.Builder;
import com.solvd.internet_store.utils.factories.AbstractFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Executor {
    private static final Logger LOGGER = LogManager.getLogger(Executor.class);

    public static void main(String[] args) {
        IBaseDao dao = new AbstractFactory().createFactory(FactoryTypes.MYBATIS).
                createDao(ModelType.USER);
        LOGGER.info(dao.getEntity(1));
        User user = (User) Builder.createModel(ModelType.USER);
        LOGGER.info(user);
    }
}