package com.solvd.internet_store.dao;

import com.solvd.internet_store.models.User;

import java.util.List;

public interface IUserDao extends IBaseDao<User>{
    List<User> getUsers();
}
