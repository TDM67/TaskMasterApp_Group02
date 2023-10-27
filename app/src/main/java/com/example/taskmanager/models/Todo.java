package com.example.taskmanager.models;

import java.io.Serializable;
import java.util.List;

public class Todo implements Serializable {
    private int id;
    private String name;
    private String description;
    private String category;
    private Long created;
    private Long expiry;
    private Boolean done;
    private Boolean favourite;

    public Todo() {
    }

    public Todo(int id, String name, String description, String category, Long created,
                Long expiry, Boolean done, Boolean favourite) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.created = created;
        this.expiry = expiry;
        this.done = done;
        this.favourite = favourite;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
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

    public Long getExpiry() {
        return expiry;
    }

    public void setExpiry(Long expiry) {
        this.expiry = expiry;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }
}