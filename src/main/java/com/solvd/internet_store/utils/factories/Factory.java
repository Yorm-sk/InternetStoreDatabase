package com.solvd.internet_store.utils.factories;

import com.solvd.internet_store.dao.CommonAbstractDao;
import com.solvd.internet_store.dao.IBaseDao;
import com.solvd.internet_store.dao.mybatis.AbstractDao;
import com.solvd.internet_store.enums.ModelType;

public abstract class Factory {
    abstract public CommonAbstractDao createDao(ModelType type);
}
