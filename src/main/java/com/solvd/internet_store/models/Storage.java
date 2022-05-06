package com.solvd.internet_store.models;

import java.util.List;

public class Storage {
    private long id;
    private String name;
    private long capacity;
    private List<DeliveryService> deliveryServices;
    private List<Product> products;

    public Storage() {
    }

    public Storage(long id, String name, long capacity, List<DeliveryService> deliveryServices, List<Product> products) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.deliveryServices = deliveryServices;
        this.products = products;
    }

    public Storage(long id, String name, long capacity, List<DeliveryService> deliveryServices) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.deliveryServices = deliveryServices;
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

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public List<DeliveryService> getDeliveryServices() {
        return deliveryServices;
    }

    public void setDeliveryServices(List<DeliveryService> deliveryServices) {
        this.deliveryServices = deliveryServices;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", deliveryServices=" + deliveryServices +
                ", products=" + products +
                '}';
    }
}
