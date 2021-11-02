package com.codegym.model.admin;

import java.util.Date;

public class Customer extends Person {
    private int id;
    private String password;
    private int status;
    private Date created;
    private Date updated;

    public Customer() {
    }

    public Customer(String name, String phone, String email, String address, int gender, String password, int status) {
        super(name, phone, email, address, gender);
        this.password = password;
        this.status = status;
    }

    public Customer(String name, String phone, String email, String address, int gender, int id, String password, int status) {
        super(name, phone, email, address, gender);
        this.id = id;
        this.password = password;
        this.status = status;
    }

    public Customer(String name, String phone, String email, String address, int gender, int id, String password, int status, Date created, Date updated) {
        super(name, phone, email, address, gender);
        this.id = id;
        this.password = password;
        this.status = status;
        this.created = created;
        this.updated = updated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
