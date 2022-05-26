package com.solvd.internet_store.dao.jdbc;

import com.solvd.internet_store.dao.ICostumerDao;
import com.solvd.internet_store.models.Costumer;
import com.solvd.internet_store.models.Order;
import com.solvd.internet_store.models.Product;
import com.solvd.internet_store.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CostumerDao extends AbstractDao implements ICostumerDao {
    private static final Logger LOGGER = LogManager.getLogger(CostumerDao.class);

    @Override
    public Costumer getEntity(long id) {
        LOGGER.info("Getting costumer from database...");
        Costumer costumer = new Costumer();
        setConnection();
        setPreparedStatement("SELECT * FROM costumers WHERE id = ?");
        try {
            preparedStatement.setLong(1, id);
            setResultSet();
            resultSet.next();
            costumer.setId(resultSet.getLong("id"));
            costumer.setPhoneNumber(resultSet.getString("phone_number"));
            setPreparedStatement("SELECT * FROM users WHERE id = ?");
            preparedStatement.setLong(1, resultSet.getLong("user_id"));
            setResultSet();
            resultSet.next();
            costumer.setUser(new User(resultSet.getLong("id"), resultSet.getString("name"),
                    resultSet.getString("email"), resultSet.getShort("age")));
            costumer.setOrders(getCostumerOrders(costumer.getId()));
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
        return costumer;
    }

    private List<Order> getCostumerOrders(long costumer_id) {
        List<Order> orders = new ArrayList<>();
        setPreparedStatement("SELECT * FROM orders where costumer_id = ?");
        try {
            preparedStatement.setLong(1, costumer_id);
            setResultSet();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setDate(resultSet.getString("date"));
                order.setType(resultSet.getString("type"));
                order.setCostumerId(resultSet.getLong("costumer_id"));
                order.setDeliveryServiceId(resultSet.getLong("delivery_service_id"));
                order.setProducts(getOrdersProducts(order.getId()));
                orders.add(order);
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        return orders;
    }

    private List<Product> getOrdersProducts(long orderID) {
        List<Product> products = new ArrayList<>();
        setPreparedStatement("SELECT p.id, p.type FROM orders_has_products  op\n" +
                "JOIN orders o on o.id = op.order_id\n" +
                "JOIN products p on p.id = op.product_id\n" +
                "WHERE o.id = ?");
        try {
            preparedStatement.setLong(1, orderID);
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

    public void showCostumersFromDatabase() {
        LOGGER.info("Show costumers from database");
        setConnection();
        setPreparedStatement("SELECT * FROM costumers");
        setResultSet();
        try {
            while (resultSet.next()) {
                LOGGER.info("Costumer id - " + resultSet.getLong("id") + ", " +
                        "phone_number - '" + resultSet.getString("phone_number") +"', " +
                        "user_id - " + resultSet.getLong("user_id"));
            }
        } catch (SQLException e){
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done..");
    }

    @Override
    public void createEntity(Costumer costumer) {
        LOGGER.info("Adding costumer to a database");
        setConnection();
        setPreparedStatement("INSERT INTO costumers (phone_number, user_id) VALUES (?, ?)");
        try {
            preparedStatement.setString(1, costumer.getPhoneNumber());
            preparedStatement.setLong(2, costumer.getUser().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done");
    }

    @Override
    public void deleteEntity(Costumer costumer) {
        LOGGER.info("Deleting costumer with id - " + costumer.getId());
        setConnection();
        setPreparedStatement("DELETE FROM costumers WHERE id = ?");
        try {
            preparedStatement.setLong(1, costumer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
    }

    @Override
    public void updateEntity(Costumer costumer) {
        LOGGER.info("Updating costumer with id - " + costumer.getId());
        setConnection();
        setPreparedStatement("UPDATE costumers SET phone_number = ?, user_id = ? WHERE id = ?");
        try {
            preparedStatement.setString(1, costumer.getPhoneNumber());
            preparedStatement.setLong(2, costumer.getUser().getId());
            preparedStatement.setLong(3, costumer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        closeAll();
        LOGGER.info("Done...");
    }

    @Override
    public List<Costumer> getCostumers() {
        return null;
    }
}
