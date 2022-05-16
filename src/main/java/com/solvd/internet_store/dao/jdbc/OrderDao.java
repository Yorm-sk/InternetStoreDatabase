package com.solvd.internet_store.dao.jdbc;

import com.solvd.internet_store.dao.IOrderDao;
import com.solvd.internet_store.models.Order;
import com.solvd.internet_store.models.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends AbstractDao implements IOrderDao {
    private final static Logger LOGGER = LogManager.getLogger(OrderDao.class);

    @Override
    public Order getEntity(long id) {
        LOGGER.info("Getting order");
        Order order = new Order();
        setConnection();
        setPreparedStatement("SELECT * FROM orders WHERE id = ?");
        try {
            preparedStatement.setLong(1, id);
            setResultSet();
            resultSet.next();
            order.setId(resultSet.getLong("id"));
            order.setType(resultSet.getString("type"));
            order.setDate(resultSet.getString("date"));
            order.setCostumerId(resultSet.getLong("costumer_id"));
            order.setDeliveryServiceId(resultSet.getLong("delivery_service_id"));
            order.setProducts(getProductsForOrder(order.getId()));
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
        return order;
    }

    private List<Product> getProductsForOrder(long orderId) {
        List<Product> products = new ArrayList<>();
        setPreparedStatement("SELECT p.id, p.type FROM orders_has_products op\n" +
                "JOIN orders o on o.id = op.order_id\n" +
                "JOIN products p on p.id = op.product_id\n" +
                "WHERE o.id = ?");
        try {
            preparedStatement.setLong(1, orderId);
            setResultSet();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getLong("id"));
                product.setType(resultSet.getString("type"));
                products.add(product);
            }
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
        }
        return products;
    }

    @Override
    public void createEntity(Order order) {
        LOGGER.info("Creating order");
        setConnection();
        setPreparedStatement("INSERT INTO orders (type, date, delivery_service_id, costumer_id) VALUES (?, ?, ?, ?)");
        try {
            preparedStatement.setString(1, order.getType());
            preparedStatement.setString(2, order.getDate());
            preparedStatement.setLong(3, order.getDeliveryServiceId());
            preparedStatement.setLong(4, order.getCostumerId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
    }

    @Override
    public void deleteEntity(Order order) {
        LOGGER.info("Deleting order");
        setConnection();
        setPreparedStatement("DELETE FROM orders_has_products WHERE order_id = ?");
        try {
            preparedStatement.setLong(1, order.getId());
            preparedStatement.executeUpdate();
            setPreparedStatement("DELETE FROM orders WHERE id = ?");
            preparedStatement.setLong(1, order.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
    }

    @Override
    public void updateEntity(Order order) {
        LOGGER.info("Updating order");
        setConnection();
        setPreparedStatement("UPDATE orders SET type = ?, date = ?, delivery_service_id = ?, costumer_id = ? " +
                "WHERE id = ?");
        try {
            preparedStatement.setString(1, order.getType());
            preparedStatement.setString(2, order.getDate());
            preparedStatement.setLong(3, order.getDeliveryServiceId());
            preparedStatement.setLong(4, order.getCostumerId());
            preparedStatement.setLong(5, order.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
    }

    public void showOrders() {
        LOGGER.info("Orders:");
        setConnection();
        setPreparedStatement("SELECT * FROM orders");
        setResultSet();
        try {
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setType(resultSet.getString("type"));
                order.setDate(resultSet.getString("date"));
                order.setCostumerId(resultSet.getLong("costumer_id"));
                order.setDeliveryServiceId(resultSet.getLong("delivery_service_id"));
                LOGGER.info(order);
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("End!");
    }

    public void addProductToOrder(long orderId, long productId){
        setConnection();
        setPreparedStatement("INSERT INTO orders_has_products (order_id, product_id) VALUES (?, ?)");
        try {
            preparedStatement.setLong(1, orderId);
            preparedStatement.setLong(2, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
    }
}
