package com.solvd.internet_store.dao.mybatis;

import com.solvd.internet_store.dao.ICostumerDao;
import com.solvd.internet_store.models.Costumer;

import java.util.List;

public class CostumerDao extends AbstractDao<ICostumerDao> implements ICostumerDao {
    @Override
    public Costumer getEntity(long id) {
        setMapper();
        Costumer costumer = mapper.getEntity(id);
        closeSession();
        return costumer;
    }

    @Override
    public void createEntity(Costumer costumer) {
        setMapper();
        mapper.createEntity(costumer);
        session.commit();
        closeSession();
    }

    @Override
    public void deleteEntity(Costumer costumer) {
        setMapper();
        mapper.deleteEntity(costumer);
        session.commit();
        closeSession();
    }

    @Override
    public void updateEntity(Costumer costumer) {
        setMapper();
        mapper.updateEntity(costumer);
        session.commit();
        closeSession();
    }

    @Override
    public List<Costumer> getCostumers() {
        setMapper();
        List<Costumer> costumers = mapper.getCostumers();
        closeSession();
        return costumers;
    }

    @Override
    public void setMapper() {
        openSession();
        mapper = session.getMapper(ICostumerDao.class);
    }
}
