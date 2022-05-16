package com.solvd.internet_store.dao.jdbc;

import com.solvd.internet_store.dao.IVehicleDao;
import com.solvd.internet_store.models.Vehicle;
import com.solvd.internet_store.models.VehicleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao extends  AbstractDao implements IVehicleDao {
    private static final Logger LOGGER = LogManager.getLogger(VehicleDao.class);

    @Override
    public Vehicle getEntity(long id) {
        LOGGER.info("Getting vehicle");
        Vehicle vehicle = new Vehicle();
        setConnection();
        setPreparedStatement("SELECT * FROM vehicles WHERE id = ?");
        try {
            preparedStatement.setLong(1, id);
            setResultSet();
            resultSet.next();
            vehicle.setId(resultSet.getLong("id"));
            vehicle.setType(resultSet.getString("type"));
            vehicle.setDeliveryServiceId(resultSet.getLong("delivery_service_id"));
            vehicle.setDriverId(resultSet.getLong("driver_id"));
            vehicle.setVehicleServices(getServicesForVehicle(vehicle.getId()));

        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
        return vehicle;
    }

    private List<VehicleService> getServicesForVehicle(long vehId){
        List<VehicleService> services = new ArrayList<>();
        setPreparedStatement("SELECT vs.id, vs.name FROM vehicle_services_maintain_vehicles vsv " +
                "JOIN vehicles v on v.id = vsv.vehicle_id " +
                "JOIN vehicle_services vs on vs.id = vsv.vehicle_service_id " +
                "WHERE v.id = ?");
        try {
            preparedStatement.setLong(1, vehId);
            setResultSet();
            while (resultSet.next()){
                VehicleService service = new VehicleService();
                service.setId(resultSet.getLong("id"));
                service.setName(resultSet.getString("name"));
                services.add(service);
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        return services;
    }

    @Override
    public void createEntity(Vehicle vehicle) {
        LOGGER.info("Creating vehicle");
        setConnection();
        setPreparedStatement("INSERT INTO vehicles (type, delivery_service_id, driver_id) VALUES (?, ?, ?)");
        try {
            preparedStatement.setString(1, vehicle.getType());
            preparedStatement.setLong(2, vehicle.getDeliveryServiceId());
            preparedStatement.setLong(3, vehicle.getDriverId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
    }

    @Override
    public void deleteEntity(Vehicle vehicle) {
        LOGGER.info("Deleting vehicle");
        setConnection();
        setPreparedStatement("DELETE FROM vehicles WHERE id = ?");
        try {
            preparedStatement.setLong(1, vehicle.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Getting vehicle");
    }

    @Override
    public void updateEntity(Vehicle vehicle) {
        LOGGER.info("Updating vehicle");
        setConnection();
        setPreparedStatement("UPDATE vehicles SET type = ?, delivery_service_id = ?, driver_id = ? WHERE id = ?");
        try {
            preparedStatement.setString(1, vehicle.getType());
            preparedStatement.setLong(2, vehicle.getDeliveryServiceId());
            preparedStatement.setLong(3, vehicle.getDriverId());
            preparedStatement.setLong(4, vehicle.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
    }

    public void showVehicles(){
        LOGGER.info("Vehicles:");
        setConnection();
        setPreparedStatement("SELECT * FROM vehicles");
        setResultSet();
        try {
            while (resultSet.next()){
                Vehicle vehicle = new Vehicle();
                vehicle.setId(resultSet.getLong("id"));
                vehicle.setType(resultSet.getString("type"));
                vehicle.setDeliveryServiceId(resultSet.getLong("delivery_service_id"));
                vehicle.setDriverId(resultSet.getLong("driver_id"));
                LOGGER.info(vehicle);
            }
        } catch (SQLException e){
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("End!");
    }
}
