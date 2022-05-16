package com.solvd.internet_store.executors.deliveryWorker;

import com.solvd.internet_store.dao.jdbc.DeliveryWorkerDao;
import com.solvd.internet_store.models.DeliveryWorker;
import com.solvd.internet_store.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Executor {
    private final static Logger LOGGER = LogManager.getLogger(Executor.class);

    public static void main(String[] args) {
        DeliveryWorkerDao workerDao = new DeliveryWorkerDao();
        workerDao.showWorkers();
        User user = new User();
        user.setId(3);
        DeliveryWorker worker = new DeliveryWorker();
        worker.setUser(user);
        createNewWorker(workerDao, worker);
        workerDao.showWorkers();
        updateAndDelete(workerDao, 3);
    }

    public static void createNewWorker(DeliveryWorkerDao dao, DeliveryWorker worker){
        dao.createEntity(worker);
    }

    public static void updateAndDelete(DeliveryWorkerDao workerDao, long workerId){
        DeliveryWorker worker = workerDao.getEntity(workerId);
        worker.getUser().setId(1);
        workerDao.updateEntity(worker);
        workerDao.showWorkers();
        workerDao.deleteEntity(worker);
        workerDao.showWorkers();
    }
}
