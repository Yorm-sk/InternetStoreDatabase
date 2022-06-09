package com.solvd.internet_store.models;

import java.util.List;

public class Product extends AbstractModel{
    private long id;
    private String type;
    private List<Storage> storages;
    private List<Order> orders;

    public Product() {
    }

    public Product(long id, String type, List<Storage> storages, List<Order> orders) {
        this.id = id;
        this.type = type;
        this.storages = storages;
        this.orders = orders;
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
        return "Product{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", storages=" + storages +
                ", orders=" + orders +
                '}';
    }
}
