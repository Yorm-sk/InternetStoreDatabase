package com.solvd.internet_store.models;

public class VehicleLicence extends AbstractModel{
    private long id;
    private int number;
    private String dateOfExperience;
    private Driver driver;

    public VehicleLicence() {
    }

    public VehicleLicence(int number, String dateOfExperience, Driver driver) {
        this.number = number;
        this.dateOfExperience = dateOfExperience;
        this.driver = driver;
    }

    public VehicleLicence(long id, int number, String dateOfExperience, Driver driver) {
        this.id = id;
        this.number = number;
        this.dateOfExperience = dateOfExperience;
        this.driver = driver;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDateOfExperience() {
        return dateOfExperience;
    }

    public void setDateOfExperience(String dateOfExperience) {
        this.dateOfExperience = dateOfExperience;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "VehicleLicence{" +
                "id=" + id +
                ", number=" + number +
                ", dateOfExperience='" + dateOfExperience + '\'' +
                ", driver=" + driver +
                '}';
    }
}
