package com.solvd.internet_store.executors.deliveryService;

import com.solvd.internet_store.dao.jdbc.DeliveryServiceDao;
import com.solvd.internet_store.models.DeliveryService;
import com.solvd.internet_store.models.DeliveryWorker;
import com.solvd.internet_store.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Executor {
    private final static Logger LOGGER = LogManager.getLogger(Executor.class);

    public static void main(String[] args) {
        DeliveryServiceDao dao = new DeliveryServiceDao();
        LOGGER.info(dao.getEntity(1));
        DeliveryService deliveryService = new DeliveryService();
        deliveryService.setName("a-company");
        DeliveryWorker deliveryWorker = new DeliveryWorker();
        deliveryWorker.setId(1);
        deliveryService.setDeliveryWorker(deliveryWorker);
        createUpdateAndDeleteExample(dao, deliveryService);

    }

    public static void createUpdateAndDeleteExample(DeliveryServiceDao dao, DeliveryService service){
        dao.showAllDeliveryServices();
        dao.createEntity(service);
        dao.showAllDeliveryServices();
//        service = dao.getEntity(6);
//        service.setName("b-company");
//        dao.updateEntity(service);
//        dao.showAllDeliveryServices();
//        dao.deleteEntity(service);
//        dao.showAllDeliveryServices();
    }
}
