package com.solvd.internet_store.executors.vehicle;

import com.solvd.internet_store.dao.jdbc.VehicleDao;
import com.solvd.internet_store.models.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Executor {
    private final static Logger LOGGER = LogManager.getLogger(Executor.class);

    public static void main(String[] args) {
        VehicleDao vehicleDao = new VehicleDao();
        vehicleDao.showVehicles();
        Vehicle vehicle = new Vehicle();
        vehicle.setType("plane");
        vehicle.setDriverId(4);
        vehicle.setDeliveryServiceId(1);
        createVehicle(vehicleDao, vehicle);
        updateAndDelete(vehicleDao, 6);
    }

    public static void createVehicle(VehicleDao vehicleDao, Vehicle vehicle){
        vehicleDao.createEntity(vehicle);
        vehicleDao.showVehicles();
    }

    public static void updateAndDelete(VehicleDao vehicleDao, long vehId){
        Vehicle vehicle = vehicleDao.getEntity(vehId);
        LOGGER.info(vehicle);
        vehicle.setType("ship");
        LOGGER.info(vehicleDao.getEntity(vehId));
        vehicleDao.deleteEntity(vehicle);
        vehicleDao.showVehicles();
    }
}
