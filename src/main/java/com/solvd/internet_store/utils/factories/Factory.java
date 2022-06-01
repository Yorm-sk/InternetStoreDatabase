package com.solvd.internet_store.utils.factories;

import com.solvd.internet_store.dao.IBaseDao;
import com.solvd.internet_store.enums.ModelType;

public abstract class Factory {
    abstract public IBaseDao createDao(ModelType type);
}
