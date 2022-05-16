package com.solvd.internet_store.dao.jdbc;

import com.solvd.internet_store.dao.IVehicleLicenceDao;
import com.solvd.internet_store.models.VehicleLicence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class VehicleLicenceDao extends AbstractDao implements IVehicleLicenceDao {
    private static final Logger LOGGER = LogManager.getLogger(VehicleLicenceDao.class);

    @Override
    public VehicleLicence getEntity(long id) {
        LOGGER.info("Getting licence");
        setConnection();
        VehicleLicence licence = new VehicleLicence();
        setPreparedStatement("SELECT * FROM vehicle_licences WHERE id = ?");
        try {
            preparedStatement.setLong(1, id);
            setResultSet();
            resultSet.next();
            licence.setId(resultSet.getLong("id"));
            licence.setNumber(resultSet.getInt("number"));
            licence.setDateOfExperience(resultSet.getString("data_of_experience"));
            DriverDao driverDao = new DriverDao();
            licence.setDriver(driverDao.getEntity(resultSet.getLong("driver_id")));
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
        return licence;
    }

    @Override
    public void createEntity(VehicleLicence vehicleLicence) {
        LOGGER.info("Creating licence");
        setConnection();
        setPreparedStatement("INSERT INTO vehicle_licences (number, data_of_experience, driver_id) VALUES (?, ?, ?)");
        try {
            preparedStatement.setInt(1, vehicleLicence.getNumber());
            preparedStatement.setString(2, vehicleLicence.getDateOfExperience());
            preparedStatement.setLong(3, vehicleLicence.getDriver().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
    }

    @Override
    public void deleteEntity(VehicleLicence vehicleLicence) {
        LOGGER.info("Deleting licence");
        setConnection();
        setPreparedStatement("DELETE FROM vehicle_licences WHERE id = ?");
        try {
            preparedStatement.setLong(1, vehicleLicence.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
    }

    @Override
    public void updateEntity(VehicleLicence vehicleLicence) {
        LOGGER.info("Updating licence");
        setConnection();
        setPreparedStatement("UPDATE vehicle_licences SET number = ?, data_of_experience = ?, driver_id = ? WHERE id = ?");
        try {
            preparedStatement.setInt(1, vehicleLicence.getNumber());
            preparedStatement.setString(2, vehicleLicence.getDateOfExperience());
            preparedStatement.setLong(3, vehicleLicence.getDriver().getId());
            preparedStatement.setLong(4, vehicleLicence.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
    }

    public void showLicences(){
        LOGGER.info("Licences:");
        setConnection();
        setPreparedStatement("SELECT * FROM vehicle_licences");
        setResultSet();
        try {
            while (resultSet.next()){
                VehicleLicence licence = new VehicleLicence();
                licence.setId(resultSet.getLong("id"));
                licence.setNumber(resultSet.getInt("number"));
                licence.setDateOfExperience(resultSet.getString("data_of_experience"));
                DriverDao driverDao = new DriverDao();
                licence.setDriver(driverDao.getEntity(resultSet.getLong("driver_id")));
                LOGGER.info(licence);
            }
        } catch (SQLException e){
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("End");
    }
}
