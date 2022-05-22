package com.solvd.internet_store.models;

import java.util.List;

public class Driver {
    private long id;
    private String experience;
    private User user;
    private List<Vehicle> vehicles;

    public Driver() {
    }

    public Driver(long id, String experience, User user, List<Vehicle> vehicles) {
        this.id = id;
        this.experience = experience;
        this.user = user;
        this.vehicles = vehicles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", experience='" + experience + '\'' +
                ", user=" + user +
                ", vehicles=" + vehicles +
                '}';
    }
}
