package com.solvd.xml_parsers.jaxb;

import com.solvd.internet_store.models.Driver;
import com.solvd.internet_store.models.User;
import com.solvd.internet_store.models.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        String fileName = "src/main/resources/jaxb.xml";
        List<Vehicle> vehicles = List.of(new Vehicle(1, "car"), new Vehicle(2, "ship"));
        Driver driver = new Driver(1, "Experienced", new User(), vehicles);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Driver.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(driver, new File(fileName));
        } catch (JAXBException e) {
            LOGGER.warn(e.getMessage());
        }

        Driver driverToUnmarshal;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Driver.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            driverToUnmarshal = (Driver) unmarshaller.unmarshal(new File(fileName));
            LOGGER.info(driverToUnmarshal);
        } catch (JAXBException e) {
            LOGGER.warn(e.getMessage());
        }

    }
}
