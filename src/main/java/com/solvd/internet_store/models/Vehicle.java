package com.solvd.internet_store.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

public class Vehicle {
    private long id;
    private String type;
    private long driverId;
    private long deliveryServiceId;
    private List<VehicleService> vehicleServices;

    public Vehicle() {
    }

    public Vehicle(String type, long driverId, long deliveryServiceId, List<VehicleService> vehicleServices) {
        this.type = type;
        this.driverId = driverId;
        this.deliveryServiceId = deliveryServiceId;
        this.vehicleServices = vehicleServices;
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

    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }

    public long getDeliveryServiceId() {
        return deliveryServiceId;
    }

    public void setDeliveryServiceId(long deliveryServiceId) {
        this.deliveryServiceId = deliveryServiceId;
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
                ", driverId=" + driverId +
                ", deliveryServiceId=" + deliveryServiceId +
                ", vehicleServices=" + vehicleServices +
                '}';
    }
}
