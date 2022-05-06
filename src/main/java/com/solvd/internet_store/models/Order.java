package com.solvd.internet_store.models;

import java.util.List;

public class Order {
    private long id;
    private String type;
    private  String date;
    private DeliveryService deliveryService;
    private Costumer costumer;
    private List<Product> products;

    public Order() {
    }

    public Order(long id, String type, String date, DeliveryService deliveryService, Costumer costumer,
                 List<Product> products) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.deliveryService = deliveryService;
        this.costumer = costumer;
        this.products = products;
    }

    public Order(long id, String type, String date, DeliveryService deliveryService, Costumer costumer) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.deliveryService = deliveryService;
        this.costumer = costumer;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DeliveryService getDeliveryService() {
        return deliveryService;
    }

    public void setDeliveryService(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    public Costumer getCostumer() {
        return costumer;
    }

    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", date='" + date + '\'' +
                ", deliveryService=" + deliveryService +
                ", costumer=" + costumer +
                ", products=" + products +
                '}';
    }
}
