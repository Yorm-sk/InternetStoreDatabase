package com.solvd.internet_store.dao.jdbc;

import com.solvd.internet_store.dao.IDeliveryWorkerDao;
import com.solvd.internet_store.models.DeliveryWorker;
import com.solvd.internet_store.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class DeliveryWorkerDao extends AbstractDao implements IDeliveryWorkerDao {
    private final static Logger LOGGER = LogManager.getLogger(DeliveryWorkerDao.class);
    @Override
    public DeliveryWorker getEntity(long id) {
        LOGGER.info("Getting worker by id");
        setConnection();
        DeliveryWorker deliveryWorker = new DeliveryWorker();
        setPreparedStatement("SELECT * FROM delivery_workers WHERE id = ?");
        try {
            preparedStatement.setLong(1, id);
            setResultSet();
            resultSet.next();
            deliveryWorker.setId(resultSet.getLong("id"));
            setPreparedStatement("SELECT * FROM users WHERE id = ?");
            preparedStatement.setLong(1, resultSet.getLong("user_id"));
            setResultSet();
            resultSet.next();
            deliveryWorker.setUser(new User(resultSet.getLong("id"), resultSet.getString("name"),
                    resultSet.getString("email"), resultSet.getShort("age")));
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
        return deliveryWorker;
    }

    @Override
    public void createEntity(DeliveryWorker deliveryWorker) {
        LOGGER.info("Creating new worker");
        setConnection();
        setPreparedStatement("INSERT INTO delivery_workers (user_id) VALUES (?)");
        try {
            preparedStatement.setLong(1, deliveryWorker.getUser().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
    }

    @Override
    public void deleteEntity(DeliveryWorker deliveryWorker) {
        LOGGER.info("Deleting worker");
        setConnection();
        setPreparedStatement("DELETE FROM delivery_workers WHERE id = ?");
        try {
            preparedStatement.setLong(1, deliveryWorker.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
    }

    @Override
    public void updateEntity(DeliveryWorker deliveryWorker) {
        LOGGER.info("Updating worker");
        setConnection();
        setPreparedStatement("UPDATE delivery_workers SET user_id = ? WHERE id = ?");
        try {
            preparedStatement.setLong(1, deliveryWorker.getUser().getId());
            preparedStatement.setLong(2, deliveryWorker.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
    }

    public void showWorkers(){
        setConnection();
        setPreparedStatement("SELECT * FROM delivery_workers");
        setResultSet();
        try {
            while (resultSet.next()){
                DeliveryWorker worker = new DeliveryWorker();
                worker.setId(resultSet.getLong("id"));
                UserDao userDao = UserDao.getInstance();
                worker.setUser(userDao.getEntity(resultSet.getLong("user_id")));
                LOGGER.info(worker);
            }
        } catch (SQLException e){
            LOGGER.warn(e.getMessage());
        }
        closeAll();
    }
}
