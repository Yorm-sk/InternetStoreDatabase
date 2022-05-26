package com.solvd.parsers.sax.classes_for_parsing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TeachersDAO {
    private static final Logger LOGGER = LogManager.getLogger(TeachersDAO.class);
    private List<Teacher> teachers;

    public TeachersDAO() {
        teachers = new ArrayList<>();
    }

    public TeachersDAO(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void showTeachers(){
        LOGGER.info("Showing teachers");
        teachers.forEach(LOGGER::info);
        LOGGER.info("Done...");
    }
}
