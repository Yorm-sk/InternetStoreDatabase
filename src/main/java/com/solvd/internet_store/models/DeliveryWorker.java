package com.solvd.internet_store.models;

public class DeliveryWorker extends AbstractModel{
    private long id;
    private User user;

    public DeliveryWorker() {
    }

    public DeliveryWorker(long id, User user) {
        this.id = id;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "DeliveryWorker{" +
                "id=" + id +
                ", user=" + user +
                '}';
    }
}
