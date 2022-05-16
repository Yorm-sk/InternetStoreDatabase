package com.solvd.internet_store.dao.jdbc;

import com.solvd.internet_store.dao.IVehicleServiceDao;
import com.solvd.internet_store.models.Vehicle;
import com.solvd.internet_store.models.VehicleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleServiceDao extends AbstractDao implements IVehicleServiceDao {
    private static final Logger LOGGER = LogManager.getLogger(VehicleLicenceDao.class);

    @Override
    public VehicleService getEntity(long id) {
        LOGGER.info("Getting service");
        VehicleService service = new VehicleService();
        setConnection();
        setPreparedStatement("SELECT * FROM vehicle_services WHERE id = ?");
        try {
            preparedStatement.setLong(1, id);
            setResultSet();
            resultSet.next();
            service.setId(resultSet.getLong("id"));
            service.setName(resultSet.getString("name"));
            service.setVehicles(getServiceVehicles(service.getId()));
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
        return service;
    }

    private List<Vehicle> getServiceVehicles(long servId) {
        List<Vehicle> vehicles = new ArrayList<>();
        setPreparedStatement("SELECT v.id, v.type, v.delivery_service_id, v.driver_id FROM " +
                "vehicle_services_maintain_vehicles vsv " +
                "JOIN vehicles v on v.id = vsv.vehicle_id " +
                "JOIN vehicle_services vs on vs.id = vsv.vehicle_service_id " +
                "WHERE vs.id = ?");
        try {
            preparedStatement.setLong(1, servId);
            setResultSet();
            while (resultSet.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(resultSet.getLong("id"));
                vehicle.setType(resultSet.getString("type"));
                vehicle.setDeliveryServiceId(resultSet.getLong("delivery_service_id"));
                vehicle.setDriverId(resultSet.getLong("driver_id"));
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        return vehicles;
    }

    @Override
    public void createEntity(VehicleService vehicleService) {
        LOGGER.info("Creating service");
        setConnection();
        setPreparedStatement("INSERT INTO vehicle_services (name) VALUES (?)");
        try {
            preparedStatement.setString(1, vehicleService.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
    }

    @Override
    public void deleteEntity(VehicleService vehicleService) {
        LOGGER.info("Deleting service");
        setConnection();
        setPreparedStatement("DELETE FROM vehicle_services WHERE id = ?");
        try {
            preparedStatement.setLong(1, vehicleService.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
    }

    @Override
    public void updateEntity(VehicleService vehicleService) {
        LOGGER.info("Updating service");
        setConnection();
        setPreparedStatement("UPDATE vehicle_services SET name = ? WHERE id = ?");
        try {
            preparedStatement.setString(1, vehicleService.getName());
            preparedStatement.setLong(2, vehicleService.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
    }

    public void showServices(){
        LOGGER.info("Services:");
        setConnection();
        setPreparedStatement("SELECT * FROM vehicle_services");
        setResultSet();
        try {
            while (resultSet.next()){
                VehicleService service = new VehicleService();
                service.setId(resultSet.getLong("id"));
                service.setName(resultSet.getString("name"));
                LOGGER.info(service);
            }
        } catch (SQLException e){
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("End");
    }
}
