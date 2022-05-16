package com.solvd.internet_store.models;

import java.util.List;

public class DeliveryService {
    private long id;
    private String name;
    private DeliveryWorker deliveryWorker;
    private List<Vehicle> vehicles;
    private List<Storage> storages;
    private List<Order> orders;

    public DeliveryService() {
    }

    public DeliveryService(String name, DeliveryWorker deliveryWorker, List<Vehicle> vehicles, List<Storage> storages,
                           List<Order> orders) {
        this.name = name;
        this.deliveryWorker = deliveryWorker;
        this.vehicles = vehicles;
        this.storages = storages;
        this.orders = orders;
    }

    public DeliveryService(String name, DeliveryWorker deliveryWorker, List<Vehicle> vehicles, List<Storage> storages) {
        this.name = name;
        this.deliveryWorker = deliveryWorker;
        this.vehicles = vehicles;
        this.storages = storages;
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

    public DeliveryWorker getDeliveryWorker() {
        return deliveryWorker;
    }

    public void setDeliveryWorker(DeliveryWorker deliveryWorker) {
        this.deliveryWorker = deliveryWorker;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Storage> getStorages() {
        return storages;
    }

    public void setStorages(List<Storage> storages) {
        this.storages = storages;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "DeliveryService{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deliveryWorker=" + deliveryWorker + "\n" +
                "vehicles=" + vehicles + "\n" +
                "storages=" + storages + "\n" +
                "orders=" + orders +
                '}';
    }
}
