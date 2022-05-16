package com.solvd.internet_store.executors.order;

import com.solvd.internet_store.dao.jdbc.OrderDao;
import com.solvd.internet_store.models.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Executor {
    private static final Logger LOGGER = LogManager.getLogger(Executor.class);

    public static void main(String[] args) {
        OrderDao orderDao = new OrderDao();
        orderDao.showOrders();
        updateAndDeleteOrder(orderDao, 2);
//        Order order = new Order();
//        order.setType("regular");
//        order.setDate("25/06/2022");
//        order.setDeliveryServiceId(1);
//        order.setCostumerId(1);
//        createOrder(orderDao, order);
//        orderDao.showOrders();


    }

    public static void createOrder(OrderDao orderDao, Order order){
        orderDao.createEntity(order);
    }

    public static void updateAndDeleteOrder(OrderDao orderDao, long orderId){
        Order order = orderDao.getEntity(orderId);
//        order.setType("slow");
//        order.setDate("19/01/2023");
//        orderDao.addProductToOrder(orderId, 2);
//        orderDao.updateEntity(order);
//        LOGGER.info(order);
        orderDao.deleteEntity(order);
        orderDao.showOrders();
    }
}
