package com.solvd.internet_store.dao;

import com.solvd.internet_store.models.Costumer;

import java.util.List;

public interface ICostumerDao extends IBaseDao<Costumer>{
    List<Costumer> getCostumers();
}
