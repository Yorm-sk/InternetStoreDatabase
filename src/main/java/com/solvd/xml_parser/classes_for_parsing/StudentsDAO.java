package com.solvd.xml_parser.classes_for_parsing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class StudentsDAO {
    private static final Logger LOGGER = LogManager.getLogger(StudentsDAO.class);
    private List<Student> students;

    public StudentsDAO() {
        students = new ArrayList<>();
    }

    public StudentsDAO(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void showStudents(){
        LOGGER.info("Showing students...");
        students.forEach(LOGGER::info);
        LOGGER.info("Done...");
    }
}
