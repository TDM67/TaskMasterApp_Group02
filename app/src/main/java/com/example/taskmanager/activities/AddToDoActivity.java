package com.example.taskmanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanager.R;
import com.example.taskmanager.adapter.MyCategoriesAdapter;
import com.example.taskmanager.helpers.LocalDBHelper;
import com.example.taskmanager.models.Category;
import com.example.taskmanager.models.Todo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddToDoActivity extends AppCompatActivity  {
    Button bAddTodo;
    Todo todo;
    Switch sAddFavourite;
    CalendarView cvAddExpiryDate;
    Calendar expiry;
    EditText etAddTime,etAddTitle,etAddDescription;
    LocalDBHelper db = new LocalDBHelper(this);
    Spinner spCategories;
    List<String> categoryList = new ArrayList<>();
    int todo_id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_todo_activity);

        Intent intent = getIntent();
        if (null != intent) {
            //Null Checking
            todo_id = intent.getIntExtra("id",0);
            if (todo_id != 0)
                getTodo(todo_id);
        }

        expiry = Calendar.getInstance(Locale.CANADA);
        todo = new Todo();
        todo.setExpiry(expiry.getTimeInMillis());

        bAddTodo = findViewById(R.id.bAddTodo);
        etAddTitle = findViewById(R.id.etAddTitle);
        etAddDescription = findViewById(R.id.etAddDescription);
        sAddFavourite = findViewById(R.id.sAddFavourite);
        cvAddExpiryDate = findViewById(R.id.cvAddExpiryDate);
        etAddTime = findViewById(R.id.etAddTime);
        spCategories = findViewById(R.id.spCategories);

        categoryList = db.getAllCategoryNames();
        if(categoryList.size() != 0) {
            ArrayAdapter<String> adp1 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, categoryList);
            adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spCategories.setAdapter(adp1);
        }else
            Toast.makeText(this, "Kindly, add a category!", Toast.LENGTH_SHORT).show();

        bAddTodo.setOnClickListener(view->{
            loadTodoTime();
            loadExpiryCalendar();
            todo.setName(etAddTitle.getText().toString());
            todo.setDescription(etAddDescription.getText().toString());
            todo.setCategory(spCategories.getSelectedItem().toString());
            todo.setCreated(System.currentTimeMillis());
            todo.setDone(false);
            todo.setFavourite(sAddFavourite.isChecked());
            todo.setExpiry(expiry.getTimeInMillis());

            boolean dbInsertionSucceeded = db.insertTodo(todo);
            if(dbInsertionSucceeded)
                Toast.makeText(this, "Task added!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Failed to add!", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });
        getSupportActionBar().setTitle("Add new task");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isValidTime(String time) {
        if (time != null && !time.isEmpty() && time.contains(":") && time.length() <= 5 && !time.startsWith(":") && !time.endsWith(":")) {
            int hour = Integer.valueOf(time.split(":")[0]);
            int minute = Integer.valueOf(time.split(":")[1]);
            return hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59;
        }
        return false;
    }

    private void loadTodoTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.GERMAN);
        etAddTime.setText(sdf.format(new Date(todo.getExpiry())));
        etAddTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String time = etAddTime.getText().toString().trim();
                if (isValidTime(time)) {
                    expiry.set(Calendar.HOUR_OF_DAY, Integer.valueOf(time.split(":")[0]));
                    expiry.set(Calendar.MINUTE, Integer.valueOf(time.split(":")[1]));
                    expiry.set(Calendar.SECOND, 0);
                    expiry.set(Calendar.MILLISECOND, 0);
                    enableAddButton();
                } else {
                    disableAddButton();
                }
            }
        });
    }

    public void loadExpiryCalendar() {
        cvAddExpiryDate.setOnDateChangeListener((@NonNull CalendarView view, int year, int month, int dayOfMonth) -> {
            expiry.set(Calendar.YEAR, year);
            expiry.set(Calendar.MONTH, month);
            expiry.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            enableAddButton();
        });
    }

    public void enableAddButton() {
        bAddTodo.setEnabled(true);
        bAddTodo.setBackgroundColor(getResources().getColor(R.color.colorAccent, null));
    }

    public void disableAddButton() {
        bAddTodo.setEnabled(false);
        bAddTodo.setBackgroundColor(getResources().getColor(R.color.colorTodoTitleDone, null));
    }

    public void getTodo(int id){
        Todo todo1 = db.getTodoById(id);
        etAddTitle.setText(todo1.getName());
        etAddDescription.setText(todo1.getDescription());
        etAddTime.setText(todo1.getExpiry()+"");
        etAddTitle.setText(todo1.getName());
        cvAddExpiryDate.setDate(todo1.getExpiry());
    }

}