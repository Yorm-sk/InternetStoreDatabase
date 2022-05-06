package com.solvd.internet_store.dao;

public interface IBaseDao <T>{
    T getEntity(long id);
    void createEntity(T t);
    void deleteEntity(T t);
    void updateEntity(T t);
}
