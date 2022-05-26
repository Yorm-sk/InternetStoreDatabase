package com.solvd.parsers.jaxb;

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
        Cat cat = new Cat(1, "Stepan", List.of(new Owner(1, "Dima", 1), new Owner(2 ,"Masha", 2))
                , "123Qf");
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Cat.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(cat, new File("src/main/resources/jaxb.xml"));
            LOGGER.info("Parsed");

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Cat catFromParser = (Cat) unmarshaller.unmarshal(new File("src/main/resources/jaxb.xml"));
            LOGGER.info(catFromParser);
        } catch (JAXBException e) {
            LOGGER.warn(e.getMessage());
        }
    }
}
