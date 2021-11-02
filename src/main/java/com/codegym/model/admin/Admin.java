package com.codegym.model.admin;

import java.util.Date;

public class Admin {
    private int id;
    private String username;
    private String password;
    private int roles;
    private Date created;
    private Date updated;

    public Admin() {
    }

    public Admin(String username, String password, int roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Admin(int id, String username, String password, int roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Admin(int id, String username, String password, int roles, Date created, Date updated) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.created = created;
        this.updated = updated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoles() {
        return roles;
    }

    public void setRoles(int roles) {
        this.roles = roles;
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

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                '}';
    }
}
