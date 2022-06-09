package com.solvd.internet_store.models;

import java.util.List;

public class Order extends AbstractModel{

    private long id;
    private String type;
    private  String date;
    private long deliveryServiceId;
    private long costumerId;
    private List<Product> products;

    public Order() {
    }

    public Order(String type, String date, long deliveryServiceId, long costumerId) {
        this.type = type;
        this.date = date;
        this.deliveryServiceId = deliveryServiceId;
        this.costumerId = costumerId;
    }

    public Order(String type, String date, long deliveryServiceId, long costumerId,
                 List<Product> products) {
        this.type = type;
        this.date = date;
        this.deliveryServiceId = deliveryServiceId;
        this.costumerId = costumerId;
        this.products = products;
    }

    public Order(long id, String type, String date, long deliveryServiceId, long costumerId) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.deliveryServiceId = deliveryServiceId;
        this.costumerId = costumerId;
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

    public long getDeliveryServiceId() {
        return deliveryServiceId;
    }

    public void setDeliveryServiceId(long deliveryServiceId) {
        this.deliveryServiceId = deliveryServiceId;
    }

    public long getCostumerId() {
        return costumerId;
    }

    public void setCostumerId(long costumerId) {
        this.costumerId = costumerId;
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
                ", deliveryServiceId=" + deliveryServiceId +
                ", costumerId=" + costumerId +
                ", products=" + products +
                '}';
    }
}
