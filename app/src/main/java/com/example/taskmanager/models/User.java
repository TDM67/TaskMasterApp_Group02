package com.example.taskmanager.models;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.taskmanager.helpers.LocalDBHelper;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String name;
    private String email;
    private Long created;
    private String password;

    public User() {
    }

    public User(int id, String name, String email, String password, Long created) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.created = created;
    }

    public static User createFrom(Cursor cursorTodo) {
        int id = cursorTodo.getInt(0);
        String name = cursorTodo.getString(1);
        String email = cursorTodo.getString(2);
        String password = cursorTodo.getString(3);
        Long created = cursorTodo.getLong(4);
        return new User(id, name, email,password,created);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ContentValues toContentValues() {
        ContentValues cv = new ContentValues();
        if (id != 0) {
            cv.put(LocalDBHelper.COL_USER_ID, id);
        }
        cv.put(LocalDBHelper.COL_USER_NAME, name);
        cv.put(LocalDBHelper.COL_USER_EMAIL, email);
        cv.put(LocalDBHelper.COL_USER_CREATED, created);
        cv.put(LocalDBHelper.COL_USER_PASSWORD, password);
        return cv;
    }
}