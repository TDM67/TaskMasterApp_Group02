package com.example.taskmanager.models;

import android.content.ContentValues;
import android.database.Cursor;

import java.io.Serializable;
import com.example.taskmanager.helpers.LocalDBHelper;

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

    public static Todo createFrom(Cursor cursorTodo) {
        int id = cursorTodo.getInt(0);
        String name = cursorTodo.getString(1);
        String description = cursorTodo.getString(2);
        String category = cursorTodo.getString(3);
        Long created = cursorTodo.getLong(4);
        Long expiry = cursorTodo.getLong(5);
        Boolean done = cursorTodo.getInt(6) != 0;
        Boolean favourite = cursorTodo.getInt(7) != 0;
        return new Todo(id, name, description, category, created, expiry, done, favourite);
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

    public ContentValues toContentValues() {
        ContentValues cv = new ContentValues();
        if (id != 0) {
            cv.put(LocalDBHelper.COL_TODO_ID, id);
        }
        cv.put(LocalDBHelper.COL_TODO_NAME, name);
        cv.put(LocalDBHelper.COL_TODO_DESCRIPTION, description);
        cv.put(LocalDBHelper.COL_TODO_CATEGORY, category);
        cv.put(LocalDBHelper.COL_TODO_CREATED, created);
        cv.put(LocalDBHelper.COL_TODO_EXPIRY, expiry);
        cv.put(LocalDBHelper.COL_TODO_DONE, done ? true : false);
        cv.put(LocalDBHelper.COL_TODO_FAVOURITE, favourite ? true : false);
        return cv;
    }

    @Override
    public String toString() {
        return "Todo {id = " + id + ", name = " + name + ", description = " + description + ", category = "+category+
                ", "+created+", expiry = " + expiry + ", done = " + done + ", favourite = " + favourite;
    }
}