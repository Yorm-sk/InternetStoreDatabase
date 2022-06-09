package com.solvd.internet_store.dao.mybatis;

import com.solvd.internet_store.dao.IUserDao;
import com.solvd.internet_store.models.User;
import com.solvd.internet_store.utils.listener.ActionListener;

import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<IUserDao> implements IUserDao {

    List<ActionListener> listeners = new ArrayList<>();

    public void addListener(ActionListener listener){
        listeners.add(listener);
    }
    @Override
    public User getEntity(long id) {
        setMapper();
        User user = mapper.getEntity(id);
        addListener(user);
        for (ActionListener ls : listeners) ls.doSomeActionOnEvent();
        closeSession();
        return user;
    }

    @Override
    public void createEntity(User user) {
        setMapper();
        mapper.createEntity(user);
        session.commit();
        closeSession();
    }

    @Override
    public void deleteEntity(User user) {
        setMapper();
        mapper.deleteEntity(user);
        session.commit();
        closeSession();
    }

    @Override
    public void updateEntity(User user) {
        setMapper();
        mapper.updateEntity(user);
        session.commit();
        closeSession();
    }

    @Override
    public List<User> getUsers() {
        setMapper();
        List<User> users= mapper.getUsers();
        listeners.addAll(users);
        for (ActionListener ls : listeners) ls.doSomeActionOnEvent();
        closeSession();
        return users;
    }

    @Override
    public void setMapper() {
        openSession();
        mapper = session.getMapper(IUserDao.class);
    }
}
