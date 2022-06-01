package com.solvd.internet_store.dao.mybatis;

import com.solvd.internet_store.utils.MyBatisSQLFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractDao <T> {
    private static final Logger LOGGER = LogManager.getLogger();

    protected SqlSession session;
    protected T mapper;

    public void openSession(){
        MyBatisSQLFactory factory = MyBatisSQLFactory.newInstance("myBatis/my_butis_confguration.xml");
        session = factory.getFactory().openSession();
    }

    public void closeSession(){
        if (session != null) session.close();
    }

    abstract public void setMapper();
}
