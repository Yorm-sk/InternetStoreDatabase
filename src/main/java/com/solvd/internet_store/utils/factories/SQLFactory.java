package com.solvd.internet_store.utils.factories;

import com.solvd.internet_store.dao.IBaseDao;
import com.solvd.internet_store.dao.jdbc.*;
import com.solvd.internet_store.enums.ModelType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SQLFactory extends Factory{
    private static final Logger LOGGER = LogManager.getLogger(SQLFactory.class);

    public IBaseDao createDao(ModelType type){
        switch (type){
            case COSTUMER:
                return new CostumerDao();
            case USER:
                return UserDao.getInstance();
            case ORDER:
                return new OrderDao();
            case DRIVER:
                return new DriverDao();
            case PRODUCT:
                return new ProductDao();
            case STORAGE:
                return new StorageDao();
            case VEHICLE:
                return new VehicleDao();
            case DELIVERY_WORKER:
                return new DeliveryWorkerDao();
            case DELIVERY_SERVICE:
                return new DeliveryServiceDao();
            case VEHICLE_LICENCE:
                return new VehicleLicenceDao();
            case VEHICLE_SERVICE:
                return new VehicleServiceDao();
            default:
                LOGGER.warn("There is no such dao");
                return null;
        }
    }
}
