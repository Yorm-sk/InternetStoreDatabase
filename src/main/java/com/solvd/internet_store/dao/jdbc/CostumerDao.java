package com.solvd.internet_store.dao.jdbc;

import com.solvd.internet_store.dao.ICostumerDao;
import com.solvd.internet_store.models.Costumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CostumerDao implements ICostumerDao {
    private static final Logger LOGGER = LogManager.getLogger(CostumerDao.class);

    @Override
    public Costumer getEntity(long id) {
        return null;
    }

    @Override
    public void createEntity(Costumer costumer) {

    }

    @Override
    public void deleteEntity(Costumer costumer) {

    }

    @Override
    public void updateEntity(Costumer costumer) {

    }
}
