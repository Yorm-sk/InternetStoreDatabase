package com.solvd.internet_store.models;

import java.util.List;

public class Costumer {
    private long id;
    private String phoneNumber;
    private User user;
    private List<Order> orders;

    public Costumer() {
    }

    public Costumer(String phoneNumber, User user, List<Order> orders) {
        this.phoneNumber = phoneNumber;
        this.user = user;
        this.orders = orders;
    }

    public Costumer(String phoneNumber, User user) {
        this.phoneNumber = phoneNumber;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
                "phone_number='" + phoneNumber + '\'' + "\n" +
                "user=" + user + "\n" +
                "orders=" + orders +
                '}';
    }
}
