package com.solvd.internet_store.models;

import com.solvd.internet_store.utils.listener.ActionListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class User extends AbstractModel implements ActionListener {
    private final static Logger LOGGER = LogManager.getLogger(User.class);
    private long id;
    private String name;
    private String email;
    private short age;

    public User() {
    }


    public User(long id) {
        this.id = id;
    }

    public User(String name, String email, short age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public User(long id, String name, String email, short age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && age == user.age && Objects.equals(name, user.name) && Objects.equals(email, user.email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public void doSomeActionOnEvent() {
        LOGGER.info("New user was created, now it is more of us!");
    }
}
