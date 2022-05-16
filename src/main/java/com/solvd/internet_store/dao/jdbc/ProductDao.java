package com.solvd.internet_store.dao.jdbc;

import com.solvd.internet_store.dao.IProductDao;
import com.solvd.internet_store.models.Order;
import com.solvd.internet_store.models.Product;
import com.solvd.internet_store.models.Storage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao extends AbstractDao implements IProductDao {
    private static final Logger LOGGER = LogManager.getLogger(ProductDao.class);

    @Override
    public Product getEntity(long id) {
        LOGGER.info("Getting product");
        Product product = new Product();
        setConnection();
        setPreparedStatement("SELECT * FROM products WHERE id = ?");
        try {
            preparedStatement.setLong(1, id);
            setResultSet();
            resultSet.next();
            product.setId(resultSet.getLong("id"));
            product.setType(resultSet.getString("type"));
            product.setOrders(getOrdersForProduct(product.getId()));
            product.setStorages(getStoragesForProduct(product.getId()));
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
        return product;
    }

    private List<Order> getOrdersForProduct(long productId){
        List<Order> orders = new ArrayList<>();
        setPreparedStatement("SELECT o.id, o.type, o.date, o.delivery_service_id, o.costumer_id FROM orders_has_products op " +
                "JOIN orders o on o.id = op.order_id " +
                "JOIN products p on p.id = op.product_id " +
                "WHERE p.id = ?");
        try {
            preparedStatement.setLong(1, productId);
            setResultSet();
            while (resultSet.next()){
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

    private List<Storage> getStoragesForProduct(long productId){
        List<Storage> storages = new ArrayList<>();
        setPreparedStatement("SELECT s.id, s.name, s.capacity FROM storages_contain_products sp " +
                "JOIN storages s on s.id = sp.storage_id " +
                "JOIN products p on p.id = sp.product_id " +
                "WHERE p.id = ?");
        try {
            preparedStatement.setLong(1, productId);
            setResultSet();
            while (resultSet.next()){
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

    @Override
    public void createEntity(Product product) {
        LOGGER.info("Creating product");
        setConnection();
        setPreparedStatement("INSERT INTO products (type) VALUES (?)");
        try {
            preparedStatement.setString(1, product.getType());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
    }

    @Override
    public void deleteEntity(Product product) {
        LOGGER.info("Deleting product");
        setConnection();
        setPreparedStatement("DELETE FROM storages_contain_products WHERE product_id = ?");
        try {
            preparedStatement.setLong(1, product.getId());
            preparedStatement.executeUpdate();
            setPreparedStatement("DELETE FROM products WHERE id = ?");
            preparedStatement.setLong(1, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
    }

    @Override
    public void updateEntity(Product product) {
        LOGGER.info("Updating product");
        setConnection();
        setPreparedStatement("UPDATE products SET type = ? WHERE id = ?");
        try {
            preparedStatement.setString(1, product.getType());
            preparedStatement.setLong(2, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
    }

    public void showProducts(){
        LOGGER.info("Products:");
        setConnection();
        setPreparedStatement("SELECT * FROM products");
        setResultSet();
        try {
            while (resultSet.next()){
                Product product = new Product();
                product.setId(resultSet.getLong("id"));
                product.setType(resultSet.getString("type"));
                LOGGER.info(product);
            }
        } catch (SQLException e){
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("End!");
    }
}
