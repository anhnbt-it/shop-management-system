package com.codegym.model.entities;

import java.sql.Timestamp;

public class Categories {
    private int id;
    private String name;
    private String description;
    private Timestamp created_at;
    private int type_id;

    public Categories(String name, String description, Timestamp created_at, int type_id) {
        this.name = name;
        this.description = description;
        this.created_at = created_at;
        this.type_id = type_id;
    }

    public Categories(int id, String name, String description, Timestamp created_at, int type_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created_at = created_at;
        this.type_id = type_id;
    }

    public Categories(int id, String name, String description, int type_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type_id = type_id;
    }

    public Categories(String name, String description, int type_id) {
        this.name = name;
        this.description = description;
        this.type_id = type_id;
    }

    public Categories() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }
}
