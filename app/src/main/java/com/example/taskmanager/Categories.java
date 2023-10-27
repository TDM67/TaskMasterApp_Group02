package com.example.taskmanager;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanager.adapter.MyCategoriesAdapter;
import com.example.taskmanager.adapter.MyListAdapter;
import com.example.taskmanager.models.Category;
import com.example.taskmanager.models.Todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Categories extends AppCompatActivity  {
    RecyclerView categoryRecycle;
    MyCategoriesAdapter myCategoriesAdapter;
    List<Category> categoryList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_activity);
        categoryRecycle = findViewById(R.id.categoryRecycle);
        getSupportActionBar().setTitle("Categories");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        long millis = new Date().getTime();

        categoryList.add(new Category(1,"Personal", millis,5,"#4055bd"));
        categoryList.add(new Category(2,"Education", millis,2,"#5d9e66"));
        categoryList.add(new Category(3,"Work", millis,4,"#9e40bd"));
        categoryList.add(new Category(4,"Entertainment", millis,15,"#ba6a14"));
        categoryList.add(new Category(5,"General", millis,3,"#7d14ba"));

        myCategoriesAdapter = new MyCategoriesAdapter(categoryList, this);
        categoryRecycle.setAdapter(myCategoriesAdapter);
        categoryRecycle.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}