package com.solvd.parsers.sax;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SAXParser {
    private static final Logger LOGGER = LogManager.getLogger(SAXParser.class);

    public static void main(String[] args) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            javax.xml.parsers.SAXParser parser = factory.newSAXParser();
            XMLHandler xmlHandler = new XMLHandler();

            parser.parse("src/main/resources/people.xml", xmlHandler);

            xmlHandler.getStudentsDAO().showStudents();
            xmlHandler.getTeachersDAO().showTeachers();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOGGER.warn(e.getMessage());
        }

    }
}
