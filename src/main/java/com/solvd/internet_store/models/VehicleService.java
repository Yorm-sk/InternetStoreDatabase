package com.solvd.internet_store.models;

import java.util.List;

public class VehicleService extends AbstractModel{
    private long id;
    private String name;
    private List<Vehicle> vehicles;

    public VehicleService() {
    }

    public VehicleService(long id, String name, List<Vehicle> vehicles) {
        this.id = id;
        this.name = name;
        this.vehicles = vehicles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "VehicleService{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", vehicles=" + vehicles +
                '}';
    }
}
