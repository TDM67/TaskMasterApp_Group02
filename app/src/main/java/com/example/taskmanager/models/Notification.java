package com.example.taskmanager.models;

import java.io.Serializable;

public class Notification implements Serializable {
    private int id;
    private String title;
    private String category;
    private String label; //color
    private Long created;
    private int priority;  // describes the task priority

    public Notification() {
    }

    public Notification(int id, String title, String category, String label,
                        Long created, int priority) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.label = label;
        this.created = created;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}