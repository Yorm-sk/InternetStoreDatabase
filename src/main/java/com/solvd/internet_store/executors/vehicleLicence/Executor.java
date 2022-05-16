package com.solvd.internet_store.executors.vehicleLicence;

import com.solvd.internet_store.dao.jdbc.VehicleLicenceDao;
import com.solvd.internet_store.models.Driver;
import com.solvd.internet_store.models.VehicleLicence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Executor {
    private static final Logger LOGGER = LogManager.getLogger(Executor.class);

    public static void main(String[] args) {
        VehicleLicenceDao licenceDao = new VehicleLicenceDao();
        licenceDao.showLicences();
        VehicleLicence licence = new VehicleLicence();
        licence.setNumber(8888);
        licence.setDateOfExperience("12/12/2012");
        Driver driver = new Driver();
        driver.setId(4);
        licence.setDriver(driver);
        create(licenceDao, licence);
        updateAndDelete(licenceDao, 3);
    }

    public static void create(VehicleLicenceDao licenceDao, VehicleLicence licence){
        licenceDao.createEntity(licence);
        licenceDao.showLicences();
    }

    public static void updateAndDelete(VehicleLicenceDao licenceDao, long licenceId){
        VehicleLicence licence = licenceDao.getEntity(licenceId);
        LOGGER.info(licence);
        licence.setNumber(99999);
        licence.setDateOfExperience("22/12/2024");
        licenceDao.updateEntity(licence);
        LOGGER.info(licenceDao.getEntity(licenceId));
        licenceDao.deleteEntity(licence);
        licenceDao.showLicences();
    }
}
