package com.solvd.internet_store.models;

import java.util.List;

public class Vehicle {
    private long id;
    private String type;
    private Driver driver;
    private DeliveryService deliveryService;
    private List<VehicleService> vehicleServices;

    public Vehicle() {
    }

    public Vehicle(long id, String type, Driver driver, DeliveryService deliveryService,
                   List<VehicleService> vehicleServices) {
        this.id = id;
        this.type = type;
        this.driver = driver;
        this.deliveryService = deliveryService;
        this.vehicleServices = vehicleServices;
    }

    public Vehicle(long id, String type, Driver driver, DeliveryService deliveryService) {
        this.id = id;
        this.type = type;
        this.driver = driver;
        this.deliveryService = deliveryService;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public DeliveryService getDeliveryService() {
        return deliveryService;
    }

    public void setDeliveryService(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    public List<VehicleService> getVehicleServices() {
        return vehicleServices;
    }

    public void setVehicleServices(List<VehicleService> vehicleServices) {
        this.vehicleServices = vehicleServices;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", driver=" + driver +
                ", deliveryService=" + deliveryService +
                ", vehicleServices=" + vehicleServices +
                '}';
    }
}