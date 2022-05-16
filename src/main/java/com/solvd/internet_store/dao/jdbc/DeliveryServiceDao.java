package com.solvd.internet_store.dao.jdbc;

import com.solvd.internet_store.dao.IDeliveryServiceDao;
import com.solvd.internet_store.models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryServiceDao extends AbstractDao implements IDeliveryServiceDao {
    private static final Logger LOGGER = LogManager.getLogger(DeliveryServiceDao.class);

    @Override
    public DeliveryService getEntity(long id) {
        LOGGER.info("Getting delivery service");
        setConnection();
        DeliveryService deliveryService = new DeliveryService();
        setPreparedStatement("SELECT * FROM delivery_services WHERE id = ?");
        try {
            preparedStatement.setLong(1, id);
            setResultSet();
            resultSet.next();
            deliveryService.setId(resultSet.getLong("id"));
            deliveryService.setName(resultSet.getString("name"));
            DeliveryWorkerDao dao = new DeliveryWorkerDao();
            DeliveryWorker deliveryWorker = dao.getEntity(resultSet.getLong("delivery_worker_id"));
            deliveryService.setDeliveryWorker(deliveryWorker);
            deliveryService.setOrders(getDeliveryServiceOrders(deliveryService.getId()));
            deliveryService.setVehicles(getDeliveryServiceVehicles(deliveryService.getId()));
            deliveryService.setStorages(getDeliveryStorages(deliveryService.getId()));
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done");
        return deliveryService;
    }

    public void showAllDeliveryServices(){
        LOGGER.info("Showing delivery service");
        setConnection();
        setPreparedStatement("SELECT * FROM delivery_services");
        setResultSet();
        try {
            while (resultSet.next()){
                DeliveryService deliveryService = new DeliveryService();
                deliveryService.setId(resultSet.getLong("id"));
                deliveryService.setName(resultSet.getString("name"));
                DeliveryWorkerDao dao = new DeliveryWorkerDao();
                deliveryService.setDeliveryWorker(dao.getEntity(resultSet.getLong("delivery_worker_id")));
                LOGGER.info("\n" +deliveryService);
            }
        } catch (SQLException e){
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
    }

    private List<Order> getDeliveryServiceOrders(long delServId) {
        List<Order> orders = new ArrayList<>();
        setPreparedStatement("SELECT * FROM orders WHERE delivery_service_id = ?");
        try {
            preparedStatement.setLong(1, delServId);
            setResultSet();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setType(resultSet.getString("type"));
                order.setDate(resultSet.getString("date"));
                order.setDeliveryServiceId(resultSet.getLong("delivery_service_id"));
                order.setCostumerId(resultSet.getLong("costumer_id"));
                orders.add(order);
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        return orders;
    }

    private List<Storage> getDeliveryStorages(long delServId) {
        List<Storage> storages = new ArrayList<>();
        setPreparedStatement("SELECT s.id, s.name, s.capacity FROM delivery_services_maintane_storages ds\n" +
                "JOIN delivery_services d on d.id = ds.delivery_service_id\n" +
                "JOIN storages s on s.id = ds.storage_id\n" +
                "WHERE d.id = ?");
        try {
            preparedStatement.setLong(1, delServId);
            setResultSet();
            while (resultSet.next()) {
                Storage storage = new Storage();
                storage.setId(resultSet.getLong("id"));
                storage.setName(resultSet.getString("name"));
                storage.setCapacity(resultSet.getLong("capacity"));
                storages.add(storage);
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        return storages;
    }

    private List<Vehicle> getDeliveryServiceVehicles(long delServId) {
        List<Vehicle> vehicles = new ArrayList<>();
        setPreparedStatement("SELECT * FROM vehicles WHERE delivery_service_id = ?");
        try {
            preparedStatement.setLong(1, delServId);
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
    public void createEntity(DeliveryService deliveryService) {
        LOGGER.info("Creating delivery service");
        setConnection();
        setPreparedStatement("INSERT INTO delivery_services (name, delivery_worker_id) VALUES (?, ?)");
        try {
            preparedStatement.setString(1, deliveryService.getName());
            preparedStatement.setLong(2, deliveryService.getDeliveryWorker().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
    }

    @Override
    public void deleteEntity(DeliveryService deliveryService) {
        LOGGER.info("Deleting delivery service");
        setConnection();
        setPreparedStatement("DELETE FROM delivery_services WHERE id = ?");
        try {
            preparedStatement.setLong(1, deliveryService.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done");

    }

    @Override
    public void updateEntity(DeliveryService deliveryService) {
        LOGGER.info("Updating delivery service");
        setConnection();
        setPreparedStatement("UPDATE delivery_services SET name = ?, delivery_worker_id = ? WHERE id = ?");
        try {
            preparedStatement.setString(1, deliveryService.getName());
            preparedStatement.setLong(2, deliveryService.getDeliveryWorker().getId());
            preparedStatement.setLong(3, deliveryService.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done");
    }
}
