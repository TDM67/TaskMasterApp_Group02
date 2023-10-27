package com.example.taskmanager.models;

import java.io.Serializable;

public class Category implements Serializable {
    private int id;
    private String name;
    private Long created;
    private int count;
    private String color;

    public Category() {
    }

    public Category(int id, String name, Long created,int count, String color) {
        this.id = id;
        this.name = name;
        this.created = created;
        this.count = count;
        this.color = color;
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

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}