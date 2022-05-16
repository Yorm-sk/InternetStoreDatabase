package com.solvd.internet_store.dao.jdbc;

import com.solvd.internet_store.dao.IDriverDao;
import com.solvd.internet_store.models.Driver;
import com.solvd.internet_store.models.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverDao extends AbstractDao implements IDriverDao {
    private static final Logger LOGGER = LogManager.getLogger(DriverDao.class);

    @Override
    public Driver getEntity(long id) {
        LOGGER.info("Getting driver");
        Driver driver = new Driver();
        setConnection();
        setPreparedStatement("SELECT * FROM drivers WHERE id = ?");
        try {
            preparedStatement.setLong(1, id);
            setResultSet();
            resultSet.next();
            driver.setId(resultSet.getLong("id"));
            driver.setExperience(resultSet.getString("experience"));
            UserDao userDao = UserDao.getInstance();
            driver.setUser(userDao.getEntity(resultSet.getLong("user_id")));
            driver.setVehicles(getDriverVehicles(driver.getId()));
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
        return driver;
    }

    private List<Vehicle> getDriverVehicles(long driverId){
        List<Vehicle> vehicles = new ArrayList<>();
        setPreparedStatement("SELECT * FROM vehicles WHERE driver_id = ?");
        try {
            preparedStatement.setLong(1, driverId);
            setResultSet();
            while (resultSet.next()){
                Vehicle vehicle = new Vehicle();
                vehicle.setId(resultSet.getLong("id"));
                vehicle.setType(resultSet.getString("type"));
                vehicle.setDeliveryServiceId(resultSet.getLong("delivery_service_id"));
                vehicle.setDriverId(driverId);
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        return vehicles;
    }
    @Override
    public void createEntity(Driver driver) {
        LOGGER.info("Creating driver");
        setConnection();
        setPreparedStatement("INSERT INTO drivers (experience, user_id) VALUES (?, ?)");
        try {
            preparedStatement.setString(1, driver.getExperience());
            preparedStatement.setLong(2, driver.getUser().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
    }

    @Override
    public void deleteEntity(Driver driver) {
        LOGGER.info("Deleting driver");
        setConnection();
        setPreparedStatement("DELETE FROM drivers WHERE id = ?");
        try {
            preparedStatement.setLong(1, driver.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done..");
    }

    @Override
    public void updateEntity(Driver driver) {
        LOGGER.info("Updating driver");
        setConnection();
        setPreparedStatement("UPDATE drivers SET experience = ?, user_id = ? WHERE id = ?");
        try {
            preparedStatement.setString(1, driver.getExperience());
            preparedStatement.setLong(2, driver.getUser().getId());
            preparedStatement.setLong(3, driver.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
    }

    public void showDrivers(){
        LOGGER.info("All drivers:");
        setConnection();
        setPreparedStatement("SELECT * FROM drivers");
        setResultSet();
        try {
            while (resultSet.next()){
                Driver driver = new Driver();
                driver.setId(resultSet.getLong("id"));
                driver.setExperience(resultSet.getString("experience"));
                UserDao userDao = UserDao.getInstance();
                driver.setUser(userDao.getEntity(resultSet.getLong("user_id")));
                LOGGER.info(driver);
            }
        } catch (SQLException e){
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("End!");
    }
}
