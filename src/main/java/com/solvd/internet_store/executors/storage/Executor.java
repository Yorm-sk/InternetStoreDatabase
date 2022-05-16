package com.solvd.internet_store.executors.storage;

import com.solvd.internet_store.dao.jdbc.StorageDao;
import com.solvd.internet_store.models.Storage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Executor {
    private static final Logger LOGGER = LogManager.getLogger(Executor.class);

    public static void main(String[] args) {
        StorageDao storageDao = new StorageDao();
        storageDao.showStorages();
//        Storage storage = new Storage();
//        storage.setName("b-storage");
//        storage.setCapacity(982);
//        createStorage(storageDao, storage);
        updateAndDelete(storageDao, 3);
    }

    public static void createStorage(StorageDao storageDao, Storage storage){
        storageDao.createEntity(storage);
        storageDao.showStorages();
    }

    public static void updateAndDelete(StorageDao storageDao, long storId){
        Storage storage = storageDao.getEntity(storId);
        LOGGER.info(storage);
        storage.setName("c-storage");
        storage.setCapacity(1000);
        storageDao.updateEntity(storage);
        LOGGER.info(storageDao.getEntity(storId));
        storageDao.showStorages();
        storageDao.deleteEntity(storage);
        storageDao.showStorages();
    }
}
