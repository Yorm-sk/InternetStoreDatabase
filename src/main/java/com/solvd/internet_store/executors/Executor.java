package com.solvd.internet_store.executors;

import com.solvd.internet_store.dao.IBaseDao;
import com.solvd.internet_store.dao.mybatis.CostumerDao;
import com.solvd.internet_store.enums.FactoryTypes;
import com.solvd.internet_store.enums.ModelType;
import com.solvd.internet_store.utils.factories.AbstractFactory;

public class Executor {
    public static void main(String[] args) {
        IBaseDao dao = new AbstractFactory().createFactory(FactoryTypes.MYBATIS).
                createDao(ModelType.COSTUMER);
        System.out.println(dao.getEntity(1));
    }
}