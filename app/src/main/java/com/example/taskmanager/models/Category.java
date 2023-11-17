package com.example.taskmanager.models;
import android.content.ContentValues;
import android.database.Cursor;

import com.example.taskmanager.helpers.LocalDBHelper;

import java.io.Serializable;

public class Category implements Serializable {
    private int id;
    private String name;
    private Long created;
    private int count;
    private String color;

    public Category() {
    }

    public Category(int id, String name, int count, String color) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.color = color;
    }

    public static Category createFrom(Cursor cursorTodo) {
        int id = cursorTodo.getInt(0);
        String name = cursorTodo.getString(1);
        int count = cursorTodo.getInt(2);
        String color = cursorTodo.getString(3);
        return new Category(id, name, count, color);
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

    public ContentValues toContentValues() {
        ContentValues cv = new ContentValues();
        if (id != 0) {
            cv.put(LocalDBHelper.COL_CAT_ID, id);
        }
        cv.put(LocalDBHelper.COL_CAT_NAME, name);
        cv.put(LocalDBHelper.COL_CAT_COUNT, count);
        cv.put(LocalDBHelper.COL_CAT_COLOR, color);
        return cv;
    }
}