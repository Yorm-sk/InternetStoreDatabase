package com.solvd.internet_store.models;

import java.util.List;

public class Costumer {
    private long id;
    private String phone_number;
    private User user;
    private List<Order> orders;

    public Costumer() {
    }

    public Costumer(String phone_number, User user, List<Order> orders) {
        this.phone_number = phone_number;
        this.user = user;
        this.orders = orders;
    }

    public Costumer(String phone_number, User user) {
        this.phone_number = phone_number;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Costumer{" +
                "id=" + id + "\n" +
                "phone_number='" + phone_number + '\'' + "\n" +
                "user=" + user + "\n" +
                "orders=" + orders +
                '}';
    }
}
