package com.solvd.internet_store.dao.jdbc;

import com.solvd.internet_store.dao.IStorageDao;
import com.solvd.internet_store.models.DeliveryService;
import com.solvd.internet_store.models.Product;
import com.solvd.internet_store.models.Storage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StorageDao extends AbstractDao implements IStorageDao {
    private static final Logger LOGGER = LogManager.getLogger(StorageDao.class);

    @Override
    public Storage getEntity(long id) {
        LOGGER.info("Getting storage");
        Storage storage = new Storage();
        setConnection();
        setPreparedStatement("SELECT * FROM storages WHERE id = ?");
        try {
            preparedStatement.setLong(1, id);
            setResultSet();
            resultSet.next();
            storage.setId(resultSet.getLong("id"));
            storage.setName(resultSet.getString("name"));
            storage.setCapacity(resultSet.getLong("capacity"));
            storage.setProducts(getProductsForStorages(storage.getId()));
            storage.setDeliveryServices(getDeliveryServicesForStorage(storage.getId()));
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
        return storage;
    }

    private List<DeliveryService> getDeliveryServicesForStorage(long storId) {
        List<DeliveryService> deliveryServices = new ArrayList<>();
        setPreparedStatement("SELECT d.id, d.name, d.delivery_worker_id FROM delivery_services_maintane_storages ds\n" +
                "JOIN delivery_services d on d.id = ds.delivery_service_id\n" +
                "JOIN storages s on s.id = ds.storage_id\n" +
                "WHERE s.id = ?");
        try {
            preparedStatement.setLong(1, storId);
            setResultSet();
            while (resultSet.next()) {
                DeliveryService deliveryService = new DeliveryService();
                deliveryService.setId(resultSet.getLong("id"));
                deliveryService.setName(resultSet.getString("name"));
                DeliveryWorkerDao dao = new DeliveryWorkerDao();
                deliveryService.setDeliveryWorker(dao.getEntity(resultSet.getLong("delivery_worker_id")));
                deliveryServices.add(deliveryService);
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        return deliveryServices;
    }

    private List<Product> getProductsForStorages(long storId) {
        List<Product> products = new ArrayList<>();
        setPreparedStatement("SELECT p.id, p.type FROM storages_contain_products sp " +
                "JOIN storages s on s.id = sp.storage_id " +
                "JOIN products p on p.id = sp.product_id " +
                "WHERE s.id = ?");
        try {
            preparedStatement.setLong(1, storId);
            setResultSet();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getLong("id"));
                product.setType(resultSet.getString("type"));
                products.add(product);
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        return products;
    }

    @Override
    public void createEntity(Storage product) {
        LOGGER.info("Creating storage");
        setConnection();
        setPreparedStatement("INSERT INTO storages (name , capacity) VALUES (?, ?)");
        try {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setLong(2, product.getCapacity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
    }

    @Override
    public void deleteEntity(Storage product) {
        LOGGER.info("Deleting storages storage");
        setConnection();
        setPreparedStatement("DELETE FROM delivery_services_maintane_storages WHERE storage_id = ?");
        try {
            preparedStatement.setLong(1, product.getId());
            preparedStatement.executeUpdate();
            setPreparedStatement("DELETE FROM storages WHERE id = ?");
            preparedStatement.setLong(1, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
    }

    @Override
    public void updateEntity(Storage product) {
        LOGGER.info("Updating storage");
        setConnection();
        setPreparedStatement("UPDATE storages SET name = ? ,capacity = ? WHERE id = ?");
        try {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setLong(2, product.getCapacity());
            preparedStatement.setLong(3, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
    }

    public void showStorages() {
        LOGGER.info("Storages:");
        setConnection();
        setPreparedStatement("SELECT * FROM storages");
        setResultSet();
        try {
            while (resultSet.next()){
                Storage storage = new Storage();
                storage.setId(resultSet.getLong("id"));
                storage.setCapacity(resultSet.getLong("capacity"));
                storage.setName(resultSet.getString("name"));
                LOGGER.info(storage);
            }
        } catch (SQLException e){
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("End");
    }
}
