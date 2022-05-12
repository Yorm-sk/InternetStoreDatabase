package com.solvd.xml_parsers.sax;

import com.solvd.xml_parsers.sax.classes_for_parsing.Student;
import com.solvd.xml_parsers.sax.classes_for_parsing.StudentsDAO;
import com.solvd.xml_parsers.sax.classes_for_parsing.Teacher;
import com.solvd.xml_parsers.sax.classes_for_parsing.TeachersDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {
    private static final Logger LOGGER = LogManager.getLogger(XMLHandler.class);
    private final TeachersDAO teachersDAO = new TeachersDAO();
    private final StudentsDAO studentsDAO = new StudentsDAO();

    public TeachersDAO getTeachersDAO() {
        return teachersDAO;
    }

    public StudentsDAO getStudentsDAO() {
        return studentsDAO;
    }

    @Override
    public void startDocument() {
        LOGGER.info("We are at begging of xml document");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals("student")) {
            String name = attributes.getValue("name");
            int age = Integer.parseInt(attributes.getValue("age"));
            studentsDAO.getStudents().add(new Student(name, age));
        }
        if (qName.equals("teacher")) {
            String name = attributes.getValue("name");
            int age = Integer.parseInt(attributes.getValue("age"));
            teachersDAO.getTeachers().add(new Teacher(name, age));
        }
    }

    @Override
    public void endDocument() {
        LOGGER.info("We are at end of xml document");
    }
}
