package com.solvd.internet_store.executors.driver;

import com.solvd.internet_store.dao.jdbc.DriverDao;
import com.solvd.internet_store.dao.jdbc.UserDao;
import com.solvd.internet_store.models.Driver;

public class Executor {
    public static void main(String[] args) {
        DriverDao driverDao = new DriverDao();
        driverDao.showDrivers();
        Driver driver = new Driver();
        driver.setExperience("new");
        UserDao userDao = UserDao.getInstance();
        driver.setUser(userDao.getEntity(1));
//        createDriver(driverDao, driver);
        updateAndDelete(driverDao, 3);
    }

    public static void createDriver(DriverDao driverDao, Driver driver) {
        driverDao.createEntity(driver);
        driverDao.showDrivers();
    }

    public static void updateAndDelete(DriverDao dao, long driverId){
        Driver driver = new Driver();
        driver = dao.getEntity(driverId);
        driver.setExperience("Prof");
        dao.updateEntity(driver);
        dao.showDrivers();
        dao.deleteEntity(driver);
        dao.showDrivers();
    }
}
