package com.solvd.internet_store.executors.vehicleService;

import com.solvd.internet_store.dao.jdbc.VehicleServiceDao;
import com.solvd.internet_store.models.VehicleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Executor {
    private static final Logger LOGGER = LogManager.getLogger(Executor.class);

    public static void main(String[] args) {
        VehicleServiceDao vehicleServiceDao = new VehicleServiceDao();
        vehicleServiceDao.showServices();
        VehicleService service = new VehicleService();
        service.setName("b-service");
        create(vehicleServiceDao, service);
        updateNadDelete(vehicleServiceDao, 3);
    }

    public static void create(VehicleServiceDao serviceDao, VehicleService service){
        serviceDao.createEntity(service);
        serviceDao.showServices();
    }

    public static void updateNadDelete(VehicleServiceDao serviceDao, long serId){
        VehicleService service = serviceDao.getEntity(serId);
        LOGGER.info(service);
        service.setName("c-service");
        serviceDao.updateEntity(service);
        LOGGER.info(serviceDao.getEntity(serId));
        serviceDao.deleteEntity(service);
        serviceDao.showServices();
    }
}
