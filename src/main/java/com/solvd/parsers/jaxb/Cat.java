package com.solvd.parsers.jaxb;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
public class Cat {
    private long id;
    private String name;
    private List<Owner> owners;
    private String password;

    public Cat() {
    }

    public Cat(long id, String name, List<Owner> owners, String password) {
        this.id = id;
        this.name = name;
        this.owners = owners;
        this.password = password;
    }
    @XmlAttribute
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @XmlElementWrapper(name = "owners")
    @XmlElement(name = "Owner")
    public List<Owner> getOwners() {
        return owners;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }
    @XmlTransient
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owners=" + owners +
                ", password='" + password + '\'' +
                '}';
    }
}
