package com.solvd.internet_store.dao.mybatis;

import com.solvd.internet_store.dao.IUserDao;
import com.solvd.internet_store.models.User;

import java.util.List;

public class UserDao extends AbstractDao<IUserDao> implements IUserDao {
    @Override
    public User getEntity(long id) {
        setMapper();
        User user = mapper.getEntity(id);
        closeSession();
        return user;
    }

    @Override
    public void createEntity(User user) {
        setMapper();
        mapper.createEntity(user);
        closeSession();
    }

    @Override
    public void deleteEntity(User user) {
        setMapper();
        mapper.deleteEntity(user);
        closeSession();
    }

    @Override
    public void updateEntity(User user) {
        setMapper();
        mapper.updateEntity(user);
        closeSession();
    }

    @Override
    public List<User> getUsers() {
        setMapper();
        List<User> users= mapper.getUsers();
        closeSession();
        return users;
    }

    @Override
    public void setMapper() {
        openSession();
        mapper = session.getMapper(IUserDao.class);
    }
}
