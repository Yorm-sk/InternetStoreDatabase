package com.solvd.internet_store.utils.factories;

import com.solvd.internet_store.enums.FactoryTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AbstractFactory {
    private static final Logger LOGGER = LogManager.getLogger(AbstractFactory.class);

    public Factory createFactory(FactoryTypes type){
        switch (type){
            case SQL:
                return new SQLFactory();
            case MYBATIS:
                return new MyBatisFactory();
            default:
                LOGGER.warn("There is no such type of factory");
                return null;
        }
    }
}
