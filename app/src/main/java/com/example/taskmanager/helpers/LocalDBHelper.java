package com.example.taskmanager.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.taskmanager.models.Category;
import com.example.taskmanager.models.Todo;
import com.example.taskmanager.helpers.LocalDBHelper;

import java.util.ArrayList;
import java.util.List;

public class LocalDBHelper extends SQLiteOpenHelper {
    public static final String COL_TODO_ID = "ID";
    public static final String COL_TODO_NAME = "NAME";
    public static final String COL_TODO_DESCRIPTION = "DESCRIPTION";
    public static final String COL_TODO_CATEGORY = "CATEGORY";
    public static final String COL_TODO_EXPIRY = "EXPIRY";
    public static final String COL_TODO_CREATED = "CREATED";
    public static final String COL_TODO_DONE = "DONE";
    public static final String COL_TODO_FAVOURITE = "FAVOURITE";
    private static final String DATABASE_NAME = "Todos.db";
    private static final String TABLE_TODOS_NAME = "TODOS";
    private static final String QUERY_CREATE_TODOS = "create table " + TABLE_TODOS_NAME + "(" + COL_TODO_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + COL_TODO_NAME + " TEXT NOT NULL, "
            + COL_TODO_DESCRIPTION + " TEXT, "+COL_TODO_CATEGORY + " TEXT, " + COL_TODO_CREATED +" TEXT, "+ COL_TODO_EXPIRY + " TEXT, " + COL_TODO_DONE
            + " boolean, " + COL_TODO_FAVOURITE + " boolean)";

    public static final String COL_CAT_ID = "ID";
    public static final String COL_CAT_NAME = "NAME";
    public static final String COL_CAT_COUNT = "COUNT";
    public static final String COL_CAT_COLOR = "COLOR";
    private static final String TABLE_CATEGORY_NAME = "CATEGORIES";

    private static final String QUERY_CREATE_CATEGORIES = "create table " + TABLE_CATEGORY_NAME + "(" + COL_CAT_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + COL_CAT_NAME + " TEXT NOT NULL UNIQUE, "
            + COL_CAT_COUNT + " INTEGER, "+COL_CAT_COLOR +" TEXT)";

    public LocalDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY_CREATE_TODOS);
        db.execSQL(QUERY_CREATE_CATEGORIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        deleteAllTodos(db);
        onCreate(db);
    }

    public List<Todo> getAllTodos() {
        List<Todo> todos = new ArrayList<>();
        Cursor result = this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_TODOS_NAME, null);
        while (result.moveToNext()) {
            todos.add(Todo.createFrom(result));
        }
        return todos;
    }

    public List<Todo> getTodosWithId(int id) {
        this.getCategoryById(id).getName();
        List<Todo> todos = new ArrayList<>();
        Cursor result = this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_TODOS_NAME + " WHERE "+this.getCategoryById(id).getName(), null);
        while (result.moveToNext()) {
            todos.add(Todo.createFrom(result));
        }
        return todos;
    }


    public Todo getTodoById(int id) {
        Todo todo = new Todo();
        Cursor result = this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_TODOS_NAME +" WHERE "+ COL_TODO_ID + "=" + id, null);

        while (result.moveToNext())
            todo = Todo.createFrom(result);

        return todo;
    }

    public List<Todo> getTodosByCategory(String category) {
        List<Todo> todos = new ArrayList<>();
        Cursor result = this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_TODOS_NAME +" WHERE "+ COL_TODO_CATEGORY + "= '" + category +"'", null);

        while (result.moveToNext())
            todos.add(Todo.createFrom(result));

        return todos;
    }

    public boolean updateTodo(Todo newTodo) {
        return this.getWritableDatabase().update(TABLE_TODOS_NAME, newTodo.toContentValues(),
                COL_TODO_ID + " = ?", new String[]{String.valueOf(newTodo.getId())}) != -1;
    }

    public boolean insertTodo(Todo todo) {
        boolean flag = this.getWritableDatabase().insert(TABLE_TODOS_NAME, null, todo.toContentValues()) != -1;
        if(flag) this.updateCategoryCount(todo.getCategory(), true);
        return  flag;
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

    public boolean insertAllTodos(List<Todo> todos) {
        for (int i = 0; i < todos.size(); i++) {
            if (!insertTodo(todos.get(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean deleteTodo(int id) {
        boolean flag =  this.getWritableDatabase().delete(TABLE_TODOS_NAME,
                COL_TODO_ID + " = ?", new String[]{String.valueOf(id)}) == 1;
        if(flag)  this.updateCategoryCount(this.getTodoById(id).getCategory(), false);
        return flag;
    }

    public void deleteAllTodos() {
        deleteAllTodos(this.getWritableDatabase());
    }

    private void deleteAllTodos(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + TABLE_TODOS_NAME);
    }

    //Categories
    public boolean insertCategory(Category category) {
        return this.getWritableDatabase().insert(TABLE_CATEGORY_NAME, null, category.toContentValues()) != -1;
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        Cursor result = this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_CATEGORY_NAME, null);
        while (result.moveToNext()) {
            categoryList.add(Category.createFrom(result));
        }
        return categoryList;
    }

    public List<String> getAllCategoryNames() {
        List<String> categoryList = new ArrayList<>();
        Cursor result = this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_CATEGORY_NAME, null);
        while (result.moveToNext()) {
            categoryList.add(Category.createFrom(result).getName());
        }
        return categoryList;
    }

    public boolean updateCategory(Category category) {
        return this.getWritableDatabase().update(TABLE_CATEGORY_NAME, category.toContentValues(),
                COL_CAT_ID + " = ?", new String[]{String.valueOf(category.getId())}) != -1;
    }

    public Category getCategoryById(int id) {
        Category category = new Category();
        Cursor result = this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_CATEGORY_NAME +" WHERE "+ COL_CAT_ID + "=" + id, null);

        while (result.moveToNext())
            category = Category.createFrom(result);

        return category;
    }

    public Category getCategoryByName(String name) {
        Category category = new Category();
        Cursor result = this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_CATEGORY_NAME +" WHERE "+ COL_CAT_NAME + "= '" + name+"' ", null);
        while (result.moveToNext())
            category = Category.createFrom(result);
        return category;
    }

    public boolean updateCategoryCount(String category, boolean flag) {
       Category category1 = this.getCategoryByName(category);
        ContentValues values = new ContentValues();
        // on below line we are passing all values
        // along with its key and value pair.
        if(flag)    values.put(COL_CAT_COUNT, category1.getCount() + 1);
        else    values.put(COL_CAT_COUNT, category1.getCount() - 1);

        return this.getWritableDatabase().update(TABLE_CATEGORY_NAME, values,
                COL_CAT_NAME + " = ?", new String[]{String.valueOf(category)}) != -1;
    }

    //delete category ---> remove /clear all todos in the category
    public boolean deleteCategory(int id) {
        return this.getWritableDatabase().delete(TABLE_CATEGORY_NAME, COL_CAT_ID + " = ?", new String[]{String.valueOf(id)}) == 1;
    }

}