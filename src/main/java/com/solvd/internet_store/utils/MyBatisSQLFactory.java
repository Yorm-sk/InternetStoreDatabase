package com.solvd.internet_store.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisSQLFactory {
    private static final Logger LOGGER = LogManager.getLogger(MyBatisSQLFactory.class);
    private static MyBatisSQLFactory factoryInstance;
    private static SqlSessionFactory factory;

    private MyBatisSQLFactory(String resources){
        try {
            InputStream inputStream = Resources.getResourceAsStream(resources);
            factory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static MyBatisSQLFactory newInstance(String resources){
        if (factoryInstance == null) factoryInstance = new MyBatisSQLFactory(resources);
        return factoryInstance;
    }

    public SqlSessionFactory getFactory(){
        return factory;
    }
}
